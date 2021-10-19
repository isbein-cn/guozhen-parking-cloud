package com.isbein.cloud.common.basic.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.isbein.cloud.common.basic.consts.WebCode;

import java.util.List;

/**
 * json响应体
 * @param <T>
 */
public class JsonResult<T> {

    private boolean result;
    private String message;
    private T data;
    @JSONField(ordinal = 100)
    private List<JsonResult.ErrorParam> errorParams;
    private JsonResult() {
    }

    public JsonResult(WebCode errorWebCode) {
        this.result = errorWebCode.getStatus() == 1;
        this.message = errorWebCode.getDesp();
    }

    public JsonResult(WebCode errorWebCode, T data) {
        this.result = errorWebCode.getStatus() == 1;
        this.message = errorWebCode.getDesp();
        this.data = data;
    }

    public JsonResult(WebCode errorWebCode, T data, String msg) {
        this.result = errorWebCode.getStatus() == 1;
        this.message = msg;
        this.data = data;
    }


    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> JsonResult<T> error(String message) {
        return new JsonResult<>(WebCode.DEFAULTERROR, null, message);
    }
    public static <T> JsonResult<T> error(String message,T object) {
        return new JsonResult<>(WebCode.DEFAULTERROR, object, message);
    }

    public static JsonResult<?> ok() {
        return new JsonResult<>(WebCode.SUCCESS);
    }
    public static JsonResult<?> okWithMessage(String msg) {
        return new JsonResult<>(WebCode.SUCCESS, null, msg);
    }

    public static <T> JsonResult<T> ok(T object) {
        return new JsonResult<>(WebCode.SUCCESS, object);
    }

    public static JsonResult<?> paramError(List<JsonResult.ErrorParam> params){
        JsonResult<?> result = new JsonResult<>();
        result.result = false;
        result.errorParams = params;
        result.message = "参数错误";
        return result;
    }

    public List<JsonResult.ErrorParam> getErrorParams() {
        return errorParams;
    }

    public void setErrorParams(List<JsonResult.ErrorParam> errorParams) {
        this.errorParams = errorParams;
    }

    public static class ErrorParam{
        private String param;
        private String message;

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
