package com.harsh.swipeandroidassignment.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.harsh.swipeandroidassignment.data.model.ProductModel;
import com.harsh.swipeandroidassignment.data.repository.ProductRepository;

import java.util.List;

public class ProductListViewModel extends ViewModel {
    private final ProductRepository productRepository;
    private final MutableLiveData<List<ProductModel>> productListLiveData = new MutableLiveData<>();

    public ProductListViewModel() {
        productRepository = new ProductRepository();
        loadProductList();
    }

    public LiveData<List<ProductModel>> getProductListLiveData() {
        return productListLiveData;
    }

    private void loadProductList() {
        productRepository.getProducts(new ProductRepository.ProductListCallback() {
            @Override
            public void onProductListFetched(List<ProductModel> productList) {
                productListLiveData.postValue(productList);
            }

            @Override
            public void onFailure(String errorMessage) {
                productListLiveData.postValue(null);
            }
        });
    }
}
