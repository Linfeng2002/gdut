package com.gdut.backend.common;

import lombok.Data;

import java.util.HashMap;
@Data
public class QueryPageParam {
    //默认值
    private static int Page_Size=20;//每页数量
    private static int Page_Num=1;//页数

    private  int PageSize=Page_Size;
    private  int PageNum=Page_Num;

    private  HashMap param;//参数
}
