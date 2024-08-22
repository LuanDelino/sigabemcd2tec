package com.cd2tec.demo.exceptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;
    private String message;
    private List<Object> data;

    public ExceptionResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.data = new ArrayList<>();
    }

    public Integer getcode() {
        return this.code;
    }

    public void setcode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Object> getData() {
        return this.data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
    
    public void addData(Object data) {
        this.data.add(data);
    }
}
