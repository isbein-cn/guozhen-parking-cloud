package com.isbein.cloud.common.basic.exception;

import com.isbein.cloud.common.basic.model.JsonResult;
import com.isbein.cloud.common.basic.model.RestResult;

import java.util.ArrayList;
import java.util.List;

public class BizException extends RuntimeException{

    private List<JsonResult.ErrorParam> params;

    private BizException() {
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, List<JsonResult.ErrorParam> params){
        super(message);
        this.params = params;
    }

    public BizException(String message,String param){
        super(message);
        JsonResult.ErrorParam errorParam = new JsonResult.ErrorParam();
        errorParam.setParam(param);
        errorParam.setMessage(message);
        this.params = new ArrayList<>();
        this.params.add(errorParam);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public List<JsonResult.ErrorParam> getParams() {
        return params;
    }

    public void setParams(List<JsonResult.ErrorParam> params) {
        this.params = params;
    }
}
