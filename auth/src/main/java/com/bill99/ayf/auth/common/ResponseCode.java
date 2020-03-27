package com.bill99.ayf.auth.common;

/**
 * andy.an
 * 2020/3/25
 */
public enum ResponseCode {

    SUCCESS("000", "成功"),
    FAILED("400", "失败"),
    ;

    private String code;
    private String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
