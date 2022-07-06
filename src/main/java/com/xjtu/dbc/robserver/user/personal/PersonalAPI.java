package com.xjtu.dbc.robserver.user.personal;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.CurrentUser;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.TokenUtils;
import com.xjtu.dbc.robserver.user.personal.entity.Avatar;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user/personal")
public class PersonalAPI {

    @Resource
    private CommonService commonService;

    @Resource
    private PersonalService personalService;
    /**
     *
     * @param avatar 头像
     * @param token
     * @return
     */
    @RequestMapping("/avatar")
    public Result getAvatar(@RequestBody Avatar avatar, @RequestHeader("Token") String token){
        CurrentUser currentUser = TokenUtils.getUserInfo(token,commonService);
        Integer userId = currentUser.getU_id();
        String url = avatar.getUrl();
        personalService.getMyAvatar(userId, url);
        return Result.successMsg("头像修改成功");
    }
}
