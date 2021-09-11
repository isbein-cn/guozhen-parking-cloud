package com.isbein.cloud.common.basic.consts;


import com.isbein.cloud.common.basic.exception.BizException;

/**
 * 状态码枚举
 * @author isbein
 */
public enum WebCode {
    /**
     * success
     */
    SUCCESS(200,1,"success","操作成功！"),

    DEFAULTERROR(8700,0,"param error","默认错误",new BizException("默认错误")),
    /**
     * 空指针
     */
    NULLPNT(8701,0,"nullpoint","空指针。可能提供的参数在数据库中没有对应的记录",new BizException("空指针。可能提供的参数在数据库中没有对应的记录")),
    /**
     * 参数有误
     */
    PARAMER(8702,0,"param error","传入的参数有误",new BizException("传入的参数有误")),
    /**
     * 数据库服务有误
     */
    SEVICEERR(8703,0,"service error","数据服务有误",new BizException("数据服务有误")),
    /**
     * 用户不存在
     */
    USER_NOT_FOUND(8201,0,"custom not found","用户不存在",new BizException("用户不存在")),
    /**
     * token错误
     */
    USER_TOKEN_ERR(8202,0,"TOKEN error","token错误",new BizException("token错误")),
    /**
     * 用户已禁用
     */
    USER_IS_LOCKED(8203,0,"custom disabled","用户已锁定",new BizException("用户已锁定")),
    /**
     * 密码错误
     */
    USER_PWD_ERR(8204,0,"password err","用户密码错误",new BizException("密码错误")),
    /**
     * 权限不足
     */
    USER_ROLE_ERR(8204,0,"role err","用户权限不足",new BizException("用户权限不足")),
    /**
     * 未知异常
     */
    EXCEPTION(8808,0,"unknow Exception","发生异常",new BizException("发生未知异常")),

    USER_NEVER_LOGIN(8809,0,"user naver login","用户未登录",new BizException("用户未登录"));


    private int code;
    private int status;
    private Exception exception;
    private String name;
    private String desp;

    WebCode(int code, int status, String name, String desp, Exception exception){
        this.setCode(code);
        this.setName(name);
        this.setDesp(desp);
        this.setStatus(status);
        this.setException(exception);
    }
    WebCode(int code, int status, String name, String desp){
        this.setCode(code);
        this.setName(name);
        this.setStatus(status);
        this.setDesp(desp);
    }
    WebCode(int code, int status, String name){
        this.setCode(code);
        this.setName(name);
        this.setStatus(status);
    }
    WebCode(int code, String name){
        this.setCode(code);
        this.setName(name);
        this.setStatus(0);
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDesp() {
        return desp;
    }
    public void setDesp(String desp) {
        this.desp = desp;
    }
    public void setException(Exception exception)
    {
        this.exception = exception;
    }
    public Exception getException(){
        return this.exception;
    }
}
