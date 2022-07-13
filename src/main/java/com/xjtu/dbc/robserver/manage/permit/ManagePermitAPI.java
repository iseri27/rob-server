package com.xjtu.dbc.robserver.manage.permit;

import com.xjtu.dbc.robserver.common.CommonService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/manage/permit")
public class ManagePermitAPI {
    @Resource
    private CommonService commonService;
    @Resource
    private ManagePermitService managePermitService;


}
