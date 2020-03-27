package com.bill99.ayf.auth.web.response;

import com.bill99.ayf.auth.common.ResponseCode;

import java.io.Serializable;

/**
 * andy.an
 * 2020/3/25
 */
public class Response<T> implements Serializable {
    private String code;
    private String message;
    private T data;

    public Response() {
    }

    public Response(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(T data) {
        this.data = data;
    }

    public static Response success() {
        return new Response(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    public static <T> Response success(T t) {
        Response response = new Response<>(t);
        response.setCode(ResponseCode.SUCCESS.getCode());
        response.setMessage(ResponseCode.SUCCESS.getMessage());
        return response;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    @Override
    public String toString() {
        return "Response {" +
                "code=" + code +
                ", message=" + message +
                ", data=" + data +
                '}';
    }
}
