package com.xjtu.dbc.robserver.common.model.inform;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xjtu.dbc.robserver.common.page.PageParam;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter @Setter
public class InformDto extends PageParam {

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date submittime;
    private String reason;
    private Integer userid;
    private Integer articleid;
}
