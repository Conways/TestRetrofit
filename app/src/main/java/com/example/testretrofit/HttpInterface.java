package com.example.testretrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HttpInterface {

	// @GET("/show/{id}")
	// Call<CookDetail> getCoodDetialById(@Path("id") int id);
	//
	// @GET("/classify/{id}")
	// Call<CookTypes> getCookTypesById(@Path("id") int id);
//	@Headers("apikey: 788117b5566e7efea9c75a89b43ce862")
	@GET("/tngou/cook/show")
	Call<CookDetail> getDetialByID(@Query("id") int id);
		

}
