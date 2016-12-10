package com.example.testretrofit;

import java.util.List;

public class HttpResult {

    public HttpResult() {
        super();
    }

    private BaseModel data;

    private int code;

    private String msg;

    private List<BaseModel> datas;


    public BaseModel getData() {
        return data;
    }

    public void setData(BaseModel data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<BaseModel> getDatas() {
        return datas;
    }

    public void setDatas(List<BaseModel> datas) {
        this.datas = datas;
    }


    @Override
    public String toString() {
        return "HttpResult{" +
                "data=" + data +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", datas=" + datas +
                '}';
    }
}
