package com.gdut.backend.common;

import lombok.Data;

@Data
public class Result {
    private String msg;
    private int code;
    private long total;
    private Object data;

    public static Result fail(){
        return result(200,"失败",0L,0);
    }

    public static Result success(Object data){
        return result(400,"成功",0L,data);
    }
    public static Result success(long total,Object data){
        return result(400,"成功",total,data);
    }
    private static Result result (int code,String msg,long total,Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setTotal(total);
        result.setData(data);
        return result;
    }
}
