package com.example.testretrofit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
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
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new MyInterceptor()).build();

        retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.106:8088")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client).build();

    }

    public HttpResult getCookDetialById(int id) {

        final HttpResult result = new HttpResult();
        HttpInterface registerInterface = retrofit.create(HttpInterface.class);
        Call<CookDetail> detaill = registerInterface.getDetialByID(8);
        Response<CookDetail> cookDetail = null;
        try {
            cookDetail = detaill.execute();
            result.setBaseModel(cookDetail.body());
            result.setCode(0);
            result.setState("succuss");
        } catch (Exception e) {
            result.setCode(1);
            result.setState(e.toString());
            e.printStackTrace();
        }
        return result;
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

    public String request() {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        String local = "http://192.168.0.106:8088";
        String httpUrl = local + "/tngou/cook/show" + "?"
                + "id=10";
//		String httpUrl = "http://apis.baidu.com/tngou/cook/show" + "?"
//				+ "id=10";

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            // ����apikey��HTTP header
            connection.setRequestProperty("apikey",
                    "788117b5566e7efea9c75a89b43ce862");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
