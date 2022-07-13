package com.xjtu.dbc.robserver.common;

public class Constants {
    public static int USER_SEX = 1;
    public static int SEX_FEMALE = 100;
    public static int SEX_MALE = 101;
    public static int SEX_UNSET = 102;

    public static int USER_STATUS;
    public static int USER_STATUS_NORMAL = 200;
    public static int USER_STATUS_MUTED = 201;
    public static int USER_STATUE_BANNED = 202;

    public static int ARTICLE_TYPE = 3;
    public static int ARTICLE_TYPE_BLOG = 300;
    public static int ARTICLE_TYPE_DYNAMIC = 301;
    public static int ARTICLE_TYPE_QUESTION = 302;
    public static int ARTICLE_TYPE_REPLY = 303;
    public static int ARTICLE_TYPE_ANSWER = 304;
    public static int ARTICLE_TYPE_GREAT_ANSWER = 305;

    public static int ARTICLE_STATUS = 4;
    public static int ARTICLE_STATUS_DRAFT = 400;
    // 已发布，待审核
    public static int ARTICLE_STATUS_WAITING_CHECK = 401;
    // 已发布且可见
    public static int ARTICLE_STATUS_VISIBLE = 402;
    public static int ARTICLE_STATUS_HIDDEN = 403;

    public static int REPORT_STATUS = 5;
    public static int REPORT_STATUS_UNCHECKED = 500;
    public static int REPORT_STATUS_ACCEPT = 501;
    public static int REPORT_STATUS_REJECT = 502;

    public static int USERLIST_TYPE = 6;
    public static int USERLIST_FOLLOW = 600;
    public static int USERLIST_FANS = 601;
    public static int USERLIST_BLACKLIST = 602;
    public static int USERLIST_CHAT = 603;

    public static int HISTORY_TYPE = 7;
    public static int HISTORY_LOGIN = 700;
    public static int HISTORY_BROWSE = 701;
    public static int HISTORY_REWARD = 702;
    public static int HISTORY_PUBLISH = 703;

    public static int VOTE_TYPE = 8;
    public static int VOTE = 800;
    public static int UNVOTE = 801;

    public static int PUNISH_TYPE = 9;
    public static int PUNISH_TYPE_MUTE = 900;
    public static int PUNISH_TYPE_FOREVER_BANNED = 901;

    public static int ROLE_TYPE = 10;
    public static int ROLE_SUPERADMIN = 1000;
    public static int ROLE_NORMAL_USER = 1001;
    public static int ROLE_ADMIN = 1002;
    // 消息类型
    public static final int MSG_TYPE = 11;
    public static final int MSG_CHAT = 1101;
    public static final int MSG_LIKE  = 1102;
    public static final int MSG_REWARD = 1103;
    public static final int MSG_FOLLOW = 1104;
    public static final int MSA_EXAMINE = 1105;

    // 模块状态
    public static final int MODULE_STATUS = 12;
    public static final int MODULE_STATUS_NORMAL = 1200;
    public static final int MODULE_STATUS_STOP_USE = 1201;
    public static final int MODULE_STATUS_PROTECT = 1202;

    /**
     * 自定义事件, 用于服务端与客户端通信
     */
    public static final String EVENT_MESSAGE_TO_SERVER = "send_trigger";
    public static final String EVENT_MESSAGE_SEND = "send";
    // Redis中敏感词集合的key
    public static final String SENSITIVE_KEY = "sensitive";
}
