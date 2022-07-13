package com.xjtu.dbc.robserver.message.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/11 15:51
 */
@Data
public class MessageDto {
    Integer msgid;
    Integer senderid;
    Integer receiverid;
    String msgcontent;
    Integer msgtype;

    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    Date time;

    Integer msgstatus;

    @Override
    public String toString(){
        return senderid + "在 "+time+" 给" +receiverid+"发送消息："+msgcontent;
    }
}
