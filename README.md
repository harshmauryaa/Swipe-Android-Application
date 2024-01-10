# Swipe Android Project

The Swipe Android Project showcases the development of two key screens within an Android app - a product listing screen and an add product screen. The primary aim is to demonstrate proficiency in building a functional and aesthetically pleasing Android application.

Google Drive Link (Signed Android App): https://drive.google.com/file/d/15LjUZt7TmCPgLi_Z79oeJ3POEIbd0ES6/view?usp=sharing

## Table of Contents

- [Introduction](#introduction)
- [Listing Products Screen (Fragment - 1)](#listing-products-screen)
  - [Features](#requirements)
  - [API Specification](#api-specification)
- [Add Product Screen](#add-product-screen)
  - [Features](#features)
  - [API Specifications for Adding Products](#api-specifications-for-adding-products)
- [Documentation Requirements](#documentation-requirements)

## Introduction

The Swipe Android Project is a demonstration of building two essential screens within an Android app: the product listing screen and the add product screen. The app aims to showcase skills in Android development by implementing a user-friendly and visually appealing interface.

## Listing Products Screen

### Features

The product listing screen has the following requirements:

- Display a scrollable list of products fetched from the provided API endpoint.
- Implement search functionality to search for specific products.
- Include navigation to transition to the Add Product screen.
- Load product images from URLs with a fallback to default images.
- Display essential product details such as name, type, price, and tax.
- Implement visual indicators like a progress bar for loading progress.
- Retrieve product data using the provided API endpoint.
- Provide a visual indicator for no internet connectivity.

### API Specification

The API endpoint contains product data in JSON format, including image URLs, price, product name, type, and tax.

Example API Response:

```json
[
    {
        "image": "URL1",
        "price": 1694.92,
        "product_name": "Testing app",
        "product_type": "Product",
        "tax": 18.0
    },
    {
        "image": "URL2",
        "price": 84745.76,
        "product_name": "Testing Update",
        "product_type": "Service",
        "tax": 18.0
    }
]
```

## Listing Products Screen

### Features

The add product screen includes the following features:

- Selection of product type from a list of options.
- Input fields for product name, selling price, and tax rate with validation.
- Submission of data using the POST method to the specified API endpoint.
- User-friendly interface with progress indicators during data upload.
- Clear feedback to users via dialogs and notifications upon completion.
- Usage of sealed classes for handling generic responses.

### API Specifications for Adding Products

- Endpoint: [Hidden]
- Body Type: form-data
- Parameters:
  - product_name (text): Product Name
  - product_type (text): Product Type
  - price (text): Selling Price
  - tax (text): Tax rate
  - files[] (text file): Images (OPTIONAL)

Expected Response:

```json
{
    "message": "Product added Successfully!",
    "product_details": {
        // Details of the added product
    },
    "product_id": 2657,
    "success": true
}
```
