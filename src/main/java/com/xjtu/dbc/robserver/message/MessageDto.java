package com.xjtu.dbc.robserver.message;

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
    String sender_name;
    String receiver_name;
    String content;
    String type;

    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "HH:mm:ss")
    Date time;

    @Override
    public String toString(){
        return sender_name + "在 "+time+" 给" +receiver_name+"发送消息："+content;
    }
}
