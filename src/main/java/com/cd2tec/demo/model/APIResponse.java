package com.cd2tec.demo.model;

import java.util.Collections;
import java.util.List;

public class APIResponse<T> {
    private String message;
    private Integer code;
    private List<T> data;

    public APIResponse() {}

    public APIResponse(String message, Integer code) {
        this.message = message;
        this.code = code;
        this.data = Collections.emptyList();
    }

    public APIResponse(String message, Integer code, List<T> data) {
        this.message = message;
        this.code = code;
        this.data = data != null ? data : Collections.emptyList();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
