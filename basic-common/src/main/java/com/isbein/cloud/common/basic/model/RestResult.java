package com.isbein.cloud.common.basic.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.isbein.cloud.common.basic.consts.WebCode;

import java.util.List;

/**
 * restful风格返回的数据格式对应实体
 *
 * @author isbein
 */
public class RestResult {

    private boolean result=true;
    private String message="";
    private Object data;
    @JSONField(serialize = false)// 此对象不再序列号成json
    private Exception exception;
    @JSONField(serialize = false)// 此对象不再序列号成json
    private WebCode errorWebCode;
    @JSONField(ordinal = 100)
    private List<ErrorParam> errorParams;
    @SuppressWarnings("unused")
    private RestResult() {
    }

    public RestResult(WebCode errorWebCode) {
        this.errorWebCode = errorWebCode;
        this.result = errorWebCode.getStatus() == 1;
        this.message = errorWebCode.getDesp();
        this.exception = errorWebCode.getException();
    }

    public RestResult(WebCode errorWebCode, Object data) {
        this.errorWebCode = errorWebCode;
        this.result = errorWebCode.getStatus() == 1;
        this.message = errorWebCode.getDesp();
        this.exception = errorWebCode.getException();
        this.data =data;
    }

    public RestResult(WebCode errorWebCode, Object data, String msg) {
        this.errorWebCode = errorWebCode;
        this.result = errorWebCode.getStatus() == 1;
        this.message = msg;
        this.exception = errorWebCode.getException();
        this.data=data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return this.exception;
    }

    public WebCode getErrorWebCode() {
        return errorWebCode;
    }

    public void setErrorWebCode(WebCode errorWebCode) {
        this.errorWebCode = errorWebCode;
    }

    public static RestResult exception() {
        return new RestResult(WebCode.EXCEPTION,null,"数据服务发生异常!请联系后台管理员");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public static RestResult error(String message) {
        return new RestResult(WebCode.DEFAULTERROR, null, message);
    }
    public static RestResult error(String message,Object object) {
        return new RestResult(WebCode.DEFAULTERROR, object, message);
    }

    public static RestResult ok() {
        return new RestResult(WebCode.SUCCESS);
    }
    public static RestResult okay(String msg) {
        return new RestResult(WebCode.SUCCESS, null, msg);
    }

    public static RestResult ok(Object object) {
        return new RestResult(WebCode.SUCCESS, object);
    }
    public static RestResult notLogin(){
        return new RestResult(WebCode.USER_NEVER_LOGIN,null,"用户未登录");
    }
    public static RestResult noRole(){
        return new RestResult(WebCode.USER_ROLE_ERR,null,"用户权限不足");
    }


    public static RestResult paramError(List<ErrorParam> params){
        RestResult result = new RestResult();
        result.result = false;
        result.errorParams = params;
        result.message = "参数错误";
        return result;
    }

    public List<ErrorParam> getErrorParams() {
        return errorParams;
    }

    public void setErrorParams(List<ErrorParam> errorParams) {
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