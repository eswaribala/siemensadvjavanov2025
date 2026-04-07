package com.siemens.productapi.services;

import com.siemens.productapi.models.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    Product updateProduct(long productId,double price);
    void deleteProduct(long  productId);
    Product findProductById(long id);
    Product findProductByName(String name);
    List<Product> findAllProducts();
}
