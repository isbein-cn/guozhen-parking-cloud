package com.isbein.cloud.common.server.api.advice;

import com.isbein.cloud.common.basic.exception.BizException;
import com.isbein.cloud.common.basic.model.RestResult;
import hprose.common.HproseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestControllerAdvice
public class ApiErrorAdvice {

    @ExceptionHandler(value = BizException.class)
    public RestResult bizException(BizException ex, HttpServletResponse response){
        List<RestResult.ErrorParam> exParams = ex.getParams();
        if (exParams != null && exParams.size() != 0){
            response.setStatus(400);
            return RestResult.paramError(exParams);
        }
        return RestResult.error(ex.getMessage());
    }

    @ExceptionHandler(value = HproseException.class)
    public RestResult hproseException(HproseException ex,HttpServletResponse response){
        String message = ex.getMessage();
        if (message.contains("BizException")){
            response.setStatus(200);
            String[] split = message.split("BizException:");
            if (split.length >= 2){
                return RestResult.error(split[1]);
            }else{
                return RestResult.error("业务发生异常");
            }
        }else{
            response.setStatus(500);
            return RestResult.error("远程服务调用异常");
        }
    }
}
