package com.harsh.swipeandroidassignment.data.model;

public class ProductResponse {
    private String message;
    private ProductModel productModel;
    private int productId;
    private boolean success;

    public ProductResponse(String message, ProductModel productModel, int productId, boolean success) {
        this.message = message;
        this.productModel = productModel;
        this.productId = productId;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ProductModel getProductModel() {
        return productModel;
    }

    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
