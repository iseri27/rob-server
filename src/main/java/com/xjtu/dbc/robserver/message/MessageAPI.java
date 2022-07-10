package com.xjtu.dbc.robserver.message;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.TokenUtils;
import com.xjtu.dbc.robserver.message.MessageService;
import com.xjtu.dbc.robserver.message.entity.ChatFriend;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/10 16:23
 */
@RestController
@RequestMapping("/message")
public class MessageAPI {

    @Resource
    MessageService messageService;

    @Resource
    CommonService commonService;

    @GetMapping("/getChatList")
    public Result getChatList(@RequestHeader("Token")String token){
        int userid = TokenUtils.getUserInfo(token, commonService).getUserid();
        List<ChatFriend> chatFriends = messageService.getChatFriends(userid);
        return  Result.success("获取私信列表成功",chatFriends);
    }

    @PostMapping("/startchat/{friendid}")
    public Result addChat(@RequestHeader("Token")String token,@PathVariable("friendid") Integer friendid){
        int userid = TokenUtils.getUserInfo(token, commonService).getUserid();
        messageService.startChat(userid,friendid);
        return  Result.successMsg("创建私信成功");
    }
}
