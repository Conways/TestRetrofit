package com.example.testretrofit;

import android.content.Context;
import android.net.Uri;

import java.io.File;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CookClient {

    private static CookClient cookClient;


    public static CookClient getInstance() {
        if (null == cookClient) {
            cookClient = new CookClient();
        }
        return cookClient;
    }

    private Retrofit retrofit;

    private CookClient() {
        init();
    }

    private void init() {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new MyInterceptor()).build();

        retrofit = new Retrofit.Builder().baseUrl("http://apis.baidu.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client).build();

    }

    public void getCookDetialById(int id, final HttpResultCallBack callBack) {
        callBack.onStart();
        final HttpInterface registerInterface = retrofit.create(HttpInterface.class);
        Call<CookDetail> detaill = registerInterface.getDetialByID(id);
        detaill.enqueue(new Callback<CookDetail>() {
            @Override
            public void onResponse(Call<CookDetail> call, Response<CookDetail> response) {
                if (response.body().getCount() == 1) {
                    callBack.onSuccuss(response.body());
                    return;
                }
                handleCustomError(response.body().getCount(), callBack);
            }
            @Override
            public void onFailure(Call<CookDetail> call, Throwable throwable) {
                handleRunTimeError(throwable, callBack);
            }
        });
    }

    public void upLoadFile(Context context, Uri uri, final HttpResultCallBack callBack){
        callBack.onStart();
        final HttpInterface upLoadFileInterface = retrofit.create(HttpInterface.class);
        File file = FileUtil.getFileByUri(context,uri);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("picture", file.getName(), requestFile);
        String descriptionString = "this is description";
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);
        Call<ResponseBody> call = upLoadFileInterface.upload(description,body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
    }


    private void handleRunTimeError(Throwable throwable, HttpResultCallBack callBack) {
        if (throwable instanceof SocketTimeoutException) {
            callBack.onFailed(new FailedResult("找不到服务器"));
        } else {
            callBack.onFailed(new FailedResult(throwable.getMessage()));
        }
    }

    private void handleCustomError(int code, HttpResultCallBack callBack) {
        String error = "未知错误!";
        switch (code) {
            case 10001:
                error = "参数不完整";
                break;
            case 1031:
                error = "用户名不存在";
                break;
            case 10003:
                error = "用户名密码错误";
                break;

            default:
                break;
        }
        callBack.onFailed(new FailedResult(error));

    }

    class MyInterceptor implements Interceptor {

        @Override
        public okhttp3.Response intercept(Chain arg0) throws IOException {
            Request request = arg0.request().newBuilder()
                    .addHeader("apikey", "788117b5566e7efea9c75a89b43ce862")
                    .build();
            return arg0.proceed(request);
        }

    }


}
