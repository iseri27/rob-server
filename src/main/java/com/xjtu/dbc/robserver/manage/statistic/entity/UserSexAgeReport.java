package com.xjtu.dbc.robserver.manage.statistic.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/12 16:11
 */
@Data
public class UserSexAgeReport {
    //用户信息分析

    //男女
    private int manNum;
    private int womanNum;

    //五个年龄段分布
    private int ageOne;
    private int ageTwo;
    private int ageThree;
    private int ageFour;
    private int ageFive;
}
