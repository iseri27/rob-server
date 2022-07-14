package com.xjtu.dbc.robserver.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
	/**
	 * 成功
	 */
	public static final int SUCCESS_CODE = 200;
	
    /**
     * 业务错误
     */
    public static final int ERR_CODE_BUSINESS = 500;

    public static final int ERR_CODE_UNLOGIN = 520;

    public static final int ERR_CODE_UNREGISTER = 540;

    public static final int ERR_CODE_ALREADYREWARD = 550;

    public static final int ERR_CODE_NOTENOUGHCOINS = 555;

    /**
     * 系统错误
     */
    public static final int ERR_CODE_SYS = 530;

    /**
     * 客户端请求有语法错误，不能被服务器所理解
     */
    public static final int BAD_REQUEST = 400;
    
    public static Result success(){
        return new Result(SUCCESS_CODE, true, null, null);
    }


    public static Result success(String message,Object data){
        return new Result(SUCCESS_CODE, true, message, data);
    }

    public static Result successMsg(String message){
        return new Result(SUCCESS_CODE, true, message, null);
    }
    
    public static Result successData(Object data){
        return new Result(SUCCESS_CODE, true, null, data);
    }
    
    public static Result fail(int code,String message){
        return new Result(code, false, message, null);
    }
    
    private int code;//200成功 500、530错误
    private Boolean success;//是否成功
    private String message;
    private Object data;
    
    private Result(int code, Boolean success, String message, Object data) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
