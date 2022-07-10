package com.xjtu.dbc.robserver.blog.reward;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.TokenUtils;
import com.xjtu.dbc.robserver.common.Utils;
import com.xjtu.dbc.robserver.level.LevelService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/blog")
public class BlogRewardAPI {
    @Resource
    private BlogRewardService blogRewardService;

    @Resource
    private LevelService levelService;

    @Resource
    private CommonService commonService;

    @PostMapping("/reward")
    public Result rewardBlog(@RequestBody RewardDto dto, @RequestHeader("Token") String token) {
        try {
            int myid = TokenUtils.getUserInfo(token, commonService).getUserid();//当前用户id
            dto.setUserid(myid);
            if(blogRewardService.getRewardHistory(dto) > 0){
                return Result.fail(Result.ERR_CODE_BUSINESS, "请勿重复打赏！");
            }
            else {
                int balance = levelService.getCans(myid);//用户代币余额
                if (balance > dto.getCoins() || balance == dto.getCoins()) {
                    dto.setRewardtime(Utils.getNow());
                    blogRewardService.insertHistory(dto);
                    levelService.updateCans(myid, (-dto.getCoins()));
                    int articleAuthorid = blogRewardService.getAuthoridByArticleid(dto.getArticleid());
                    levelService.updateCans(articleAuthorid, dto.getCoins());
                    return Result.success("打赏成功！", dto.getCoins());
                } else
                    return Result.fail(Result.ERR_CODE_BUSINESS, "余额不足！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(Result.ERR_CODE_UNLOGIN, "打赏失败，请先登录！");
        }

    }
}
