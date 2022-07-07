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

    public static int ARTICLE_STATUS = 4;
    public static int ARTICLE_STATUS_DRAFT = 400;
    public static int ARTICLE_STATUS_PUBLISH = 401;
    public static int ARTICLE_STATUS_HIDDEN = 402;

    public static int REPORT_STATUS = 5;
    public static int REPORT_STATUS_UNCHECKED = 500;
    public static int REPORT_STATUS_ACCEPT = 501;
    public static int REPORT_STATUS_REJECT = 502;

    public static int USERLIST_TYPE = 6;
    public static int USERLIST_FOLLOW = 600;
    public static int USERLIST_FANS = 601;
    public static int USERLIST_BLACKLIST = 602;

    public static int HISTORY_TYPE = 7;
    public static int HISTORY_LOGIN = 700;
    public static int HISTORY_BROWSE = 701;

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
}
