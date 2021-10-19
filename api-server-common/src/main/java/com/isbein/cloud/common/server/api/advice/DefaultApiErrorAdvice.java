package com.isbein.cloud.common.server.api.advice;

import com.isbein.cloud.common.basic.exception.BizException;
import com.isbein.cloud.common.basic.model.JsonResult;
import com.isbein.cloud.common.basic.model.RestResult;
import hprose.common.HproseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class DefaultApiErrorAdvice {

    @ExceptionHandler(value = BizException.class)
    public JsonResult<?> bizException(BizException ex, HttpServletResponse response){
        log.error("异常",ex);
        List<JsonResult.ErrorParam> exParams = ex.getParams();
        if (exParams != null && exParams.size() != 0){
            response.setStatus(400);
            return JsonResult.paramError(exParams);
        }
        return JsonResult.error(ex.getMessage());
    }

    @ExceptionHandler(value = HproseException.class)
    public JsonResult<?> hproseException(HproseException ex,HttpServletResponse response){
        log.error("远程调用异常",ex);
        String message = ex.getMessage();
        if (message.contains("BizException")){
            response.setStatus(200);
            String[] split = message.split("BizException:");
            if (split.length >= 2){
                return JsonResult.error(split[1]);
            }else{
                return JsonResult.error("业务发生异常");
            }
        }else{
            response.setStatus(500);
            return JsonResult.error("远程服务调用异常");
        }
    }
}
