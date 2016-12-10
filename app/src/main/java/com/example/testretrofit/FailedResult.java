package com.example.testretrofit;

/**
 * Created by John on 2016/11/4.
 */

public class FailedResult {


    public FailedResult(String msg) {
        this.msg = msg;
    }

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
