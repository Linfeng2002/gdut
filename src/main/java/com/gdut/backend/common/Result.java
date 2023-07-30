package com.gdut.backend.common;

import com.gdut.backend.exception.CustomizeException;
import lombok.Data;

@Data
public class Result<T> {
    private String msg;
    private int code;
    private long total;
    private T data;

    public  static Result fail(){return result(400,"失败",0L,0);}
    public  static Result fail(CustomizeException customizeException){return result(customizeException.getCode(),customizeException.getMessage(),0L,0);}


    public  static <T>Result success(T data){
        return result(200,"成功",0L,data);
    }
    public  static <T>Result success(long total,T data){
        return result(200,"成功",total,data);
    }
    public  static Result success(){
        return result(200,"成功",0L,null);
    }

    private static <T> Result result (int code,String msg,long total,T data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setTotal(total);
        result.setData(data);
        return result;
    }
}
