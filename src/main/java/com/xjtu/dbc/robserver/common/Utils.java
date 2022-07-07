package com.xjtu.dbc.robserver.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import java.text.ParseException;
import java.util.*;


import lombok.extern.slf4j.Slf4j;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/6 9:53
 */


@Slf4j
public class Utils {
    /**
     * 生成图片随机名称
     * 时间+UUID生成的随机字符串
     */
    public static String imgNameGenerator() {
        // 图片的随机名称，上传时间+UUID随机字符串
        String uuid = UUID.randomUUID().toString();
        String now = getDateString();
        return now + "_" + uuid;
    }

    /**
     * 获取并返回当前日期，返回一个字符串
     * 如：20210607
     */
    public static String getDateString() {
        //Calendar calender = Calendar.getInstance();
        Calendar calender = Calendar.getInstance();
        int year = calender.get(Calendar.YEAR);
        int month = calender.get(Calendar.MONTH) + 1;
        String mon = month < 10 ? ("0" + month) : ("" + month);
        int day = calender.get(Calendar.DAY_OF_MONTH);
        String d = day < 10 ? ("0" + day) : ("" + day);
        return Integer.toString(year) + mon + d;
    }

    /**
     * 获取并返回当前时间，返回一个字符串
     * 如：2021-07-06 14:56:00
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public static Date getNow() {
        Calendar cal = Calendar.getInstance();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.parse(dateFormat.format(cal.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
