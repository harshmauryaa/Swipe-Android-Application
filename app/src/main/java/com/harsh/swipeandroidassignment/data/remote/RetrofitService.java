package com.harsh.swipeandroidassignment.data.remote;

import com.harsh.swipeandroidassignment.data.model.ProductModel;
import com.harsh.swipeandroidassignment.data.model.ProductResponse;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitService {
    @GET("api/public/get")
    Call<List<ProductModel>> getProducts();

    @Multipart
    @POST("api/public/add")
    Call<ProductResponse> addProduct(
            @Part("product_name") RequestBody productName,
            @Part("product_type") RequestBody productType,
            @Part("price") RequestBody price,
            @Part("tax") RequestBody tax
    );
}