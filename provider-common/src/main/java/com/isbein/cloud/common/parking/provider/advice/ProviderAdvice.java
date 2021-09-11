package com.isbein.cloud.common.parking.provider.advice;

import com.isbein.cloud.common.basic.exception.BizException;
import com.isbein.cloud.common.basic.model.RestResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestControllerAdvice
public class ProviderAdvice {

    @ExceptionHandler(value = BizException.class)
    public RestResult bizException(BizException ex, HttpServletResponse response){
        List<RestResult.ErrorParam> exParams = ex.getParams();
        if (exParams != null && exParams.size() != 0){
            response.setStatus(400);
            return RestResult.paramError(exParams);
        }
        return RestResult.error(ex.getMessage());
    }
}
