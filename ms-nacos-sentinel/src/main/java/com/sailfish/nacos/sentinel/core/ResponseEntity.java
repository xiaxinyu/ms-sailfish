package com.sailfish.nacos.sentinel.core;


import java.beans.Transient;

/**
 * 公共返回信息
 * @author liuchun
 */
public class ResponseEntity<T> {

    private T data;
    /**
     * 默认成功
     */
    private Integer code = 20000;

    public transient static Integer FAIL = 30000;

    private String message;

//    private static ResponseEntity SUCCESS = new ResponseEntity();


    public static ResponseEntity ok() {
        return new ResponseEntity();
    }

    public static ResponseEntity error(String message) {
        return new ResponseEntity(30000, message);
    }

    public ResponseEntity(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseEntity(T data) {
        this.data = data;
    }

    public ResponseEntity() {

    }

    public ResponseEntity(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
