package com.harsh.swipeandroidassignment.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.harsh.swipeandroidassignment.data.model.ProductResponse;
import com.harsh.swipeandroidassignment.data.repository.ProductRepository;

public class AddProductViewModel extends ViewModel {
    private final ProductRepository productRepository;
    private final MutableLiveData<ProductResponse> productResponseLiveData = new MutableLiveData<>();

    public AddProductViewModel() {
        productRepository = new ProductRepository();
    }

    public LiveData<ProductResponse> getAddProductLiveData() {
        return productResponseLiveData;
    }


    public void addProduct(String productName, String productType, String price, String tax) {
        productRepository.addProduct(productName, productType, price, tax, new ProductRepository.AddProductCallback() {
            @Override
            public void onProductAdded(ProductResponse productResponse) {
                productResponseLiveData.postValue(productResponse);
            }

            @Override
            public void onFailure(String errorMessage) {
                productResponseLiveData.postValue(null);
            }
        });
    }
}

