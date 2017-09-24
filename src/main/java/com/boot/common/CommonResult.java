package com.boot.common;

public class CommonResult {
    private int code;
    private String message;
    private Object data;

    public static CommonResult ok(Object t){
        return new CommonResult(0,"",t);
    }

    public static CommonResult error(int code, String message){
        return new CommonResult(code,message,null);
    }

    public CommonResult(int code, String message, Object t) {
        this.code = code;
        this.message = message;
        this.data = t;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
