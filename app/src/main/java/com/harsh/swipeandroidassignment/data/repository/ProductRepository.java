package com.harsh.swipeandroidassignment.data.repository;

import androidx.annotation.NonNull;

import com.harsh.swipeandroidassignment.data.model.ProductModel;
import com.harsh.swipeandroidassignment.data.model.ProductResponse;
import com.harsh.swipeandroidassignment.data.remote.RetrofitInstance;
import com.harsh.swipeandroidassignment.data.remote.RetrofitService;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {
    private final RetrofitService retrofitService;

    public ProductRepository() {
        retrofitService = RetrofitInstance.getService();
    }

    public interface ProductListCallback {
        void onProductListFetched(List<ProductModel> productList);
        void onFailure(String errorMessage);
    }

    public void getProducts(final ProductListCallback callback) {
        retrofitService.getProducts().enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<ProductModel>> call, @NonNull Response<List<ProductModel>> response) {
                if (response.isSuccessful()) {
                    List<ProductModel> productList = response.body();
                    if (productList != null) {
                        callback.onProductListFetched(productList);
                    } else {
                        callback.onFailure("Null");
                    }
                } else {
                    callback.onFailure("Failed");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ProductModel>> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }


    public interface AddProductCallback {
        void onProductAdded(ProductResponse productResponse);
        void onFailure(String errorMessage);
    }

    public void addProduct(String productName, String productType, String price, String tax, final AddProductCallback callback) {

        RequestBody productNamePart = RequestBody.create(MediaType.parse("text/plain"), productName);
        RequestBody productTypePart = RequestBody.create(MediaType.parse("text/plain"), productType);
        RequestBody pricePart = RequestBody.create(MediaType.parse("text/plain"), price);
        RequestBody taxPart = RequestBody.create(MediaType.parse("text/plain"), tax);

        retrofitService.addProduct(productNamePart, productTypePart, pricePart, taxPart).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(@NonNull Call<ProductResponse> call, @NonNull Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    ProductResponse productResponse = response.body();
                    if (productResponse != null) {
                        callback.onProductAdded(productResponse);
                    } else {
                        callback.onFailure("Null");
                    }
                } else {
                    callback.onFailure("Failed");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ProductResponse> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

}
