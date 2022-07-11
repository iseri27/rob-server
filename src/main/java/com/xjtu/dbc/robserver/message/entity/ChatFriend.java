package com.xjtu.dbc.robserver.message.entity;

import lombok.Data;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/10 16:15
 */
@Data
public class ChatFriend {
    Integer id;//id
    String name;//昵称
    String avatar;//头像
    String unReadNum;//未读消息数
}
