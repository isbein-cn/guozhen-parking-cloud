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
    @JSONField(ordinal = 100)
    private List<ErrorParam> errorParams;
    @SuppressWarnings("unused")
    private RestResult() {
    }

    public RestResult(WebCode errorWebCode) {
        this.result = errorWebCode.getStatus() == 1;
        this.message = errorWebCode.getDesp();
    }

    public RestResult(WebCode errorWebCode, Object data) {
        this.result = errorWebCode.getStatus() == 1;
        this.message = errorWebCode.getDesp();
        this.data =data;
    }

    public RestResult(WebCode errorWebCode, Object data, String msg) {
        this.result = errorWebCode.getStatus() == 1;
        this.message = msg;
        this.data=data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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