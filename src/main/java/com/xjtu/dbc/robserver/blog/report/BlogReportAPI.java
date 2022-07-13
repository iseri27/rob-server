package com.xjtu.dbc.robserver.blog.report;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.TokenUtils;
import com.xjtu.dbc.robserver.common.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController//本注解说明本类的对象将作为受spring容器管理的对象，并且说明这是一个控制器组件(Bean)
@RequestMapping("/blog")//本注解说明本控制器内的所有路径都以之开头
public class BlogReportAPI {
    @Resource//从容器中引入依赖对象
    private BlogReportService blogReportService;

    @Resource
    private CommonService commonService;

    @PostMapping("/report")
    public Result DoInform(@RequestBody Report report, @RequestHeader("Token") String token){
        Integer myid = TokenUtils.getUserInfo(token,commonService).getUserid();//当前用户id;
        ReportDto dto = new ReportDto();
        dto.setUserid(myid);
        dto.setArticleid(report.getArticleid());
        dto.setReason(report.getReason());
        dto.setSubmittime(Utils.getNow());
        dto.setReportstatus(500);
        blogReportService.report(dto);
        return Result.successMsg("举报成功！");
    }
}
