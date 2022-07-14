package com.xjtu.dbc.robserver.user.home.entity;

import lombok.Data;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/13 20:51
 */
@Data
public class UnReadCount {
    int countSum;
    int countChat;
    int countLike;
    int countFollow;
    int countComment;
    int countAnswer;
}
