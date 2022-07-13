package com.xjtu.dbc.robserver.common;

import com.corundumstudio.socketio.SocketIOClient;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xjtu.dbc.robserver.common.page.PageParam;
import com.xjtu.dbc.robserver.common.page.QueryAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.*;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
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

    /**
     * 分页器
     */
    public static <T> Map<String, Object> getPage(PageParam pageParam, QueryAction<T> queryAction) {
        PageHelper.startPage(pageParam);

        List<T> list = queryAction.execute();

        PageInfo<T> pageInfo = new PageInfo<>(list);

        Map<String, Object> page = new HashMap<>();

        page.put("current", pageInfo.getPageNum());
        page.put("pageSize",pageInfo.getPageSize());//每页最大记录数
        page.put("total",pageInfo.getTotal());//总记录数
        page.put("pages",pageInfo.getPages());//总页数
        page.put("size",pageInfo.getSize());//当前页实际记录数
        page.put("list",pageInfo.getList());//当前页的数据记录
        page.put("first",1);
        page.put("pre",pageInfo.getPrePage());
        page.put("next",pageInfo.getNextPage());
        page.put("last",pageInfo.getPages());

        return page;
    }

    /**
     * 获取 IP 地址
     */
    public static String getIpByClient(SocketIOClient client) {
        String addr = client.getRemoteAddress().toString();
        String clientIP = addr.substring(1, addr.indexOf(":"));
        return clientIP;
    }

    /**
     * 将一个Java对象转化为json串，并向浏览器（客户端输出）
     * @param resp 相应对象
     * @param obj 需要转化为json串的java对象
     */
    public static void outJson(HttpServletResponse resp, Object obj) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");

        PrintWriter out = resp.getWriter();//获取向客户端发送字符信息流对象
        // 将list集合对象转化为json格式字符串
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(obj);
        out.print(jsonString);

        out.flush();
        out.close();
    }

}
