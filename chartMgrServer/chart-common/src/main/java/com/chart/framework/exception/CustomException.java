package com.chart.framework.exception;

import com.chart.framework.model.response.ResultCode;

/**
 * @Author: Caiqin
 * @Date: 2019/12/28 14:50
 */
public class CustomException extends RuntimeException{
    //错误代码
    ResultCode resultCode;

    public CustomException(ResultCode resultCode){
        this.resultCode = resultCode;
    }
    public ResultCode getResultCode(){
        return resultCode;
    }
}
