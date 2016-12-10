package com.example.testretrofit;

/**
 * Created by John on 2016/11/4.
 */

public interface HttpResultCallBack {
    void onStart();
    void onSuccuss(BaseModel baseModel);
    void onFailed(FailedResult failedResult);
}
