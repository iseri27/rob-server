package com.xjtu.dbc.robserver.common.model.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/14 10:26
 */
@Data
public class Message {
    Integer msgid;
    Integer senderid;
    Integer receiverid;
    String msgcontent;
    Integer msgtype;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    Date time;

    Integer msgstatus;
    Integer articleid;
}
