
package com.shopsphere.product_service;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Get all products
    public List<ProductResponse> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(ProductResponse::from)
                .toList();
    }

    // Get product by ID
    public ProductResponse getProductById(Long id) {

    Product product = productRepository.findById(id)
            .orElseThrow(() ->
                    new ProductNotFoundException("Product not found"));

    return ProductResponse.from(product);
}

    // Create product
    public ProductResponse createProduct(ProductRequest request) {

        if (productRepository.existsBySku(request.sku())) {
            throw new IllegalArgumentException("Product SKU already exists");
        }

        Product product = new Product();

        product.setName(request.name());
        product.setSku(request.sku());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setCategory(request.category());

        Product savedProduct = productRepository.save(product);

        return ProductResponse.from(savedProduct);
    }

    // Update product
    public ProductResponse updateProduct(Long id, ProductRequest request) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        if (productRepository.existsBySku(request.sku())
                && !existingProduct.getSku().equals(request.sku())) {
            throw new IllegalArgumentException("Product SKU already exists");
        }

        existingProduct.setName(request.name());
        existingProduct.setSku(request.sku());
        existingProduct.setDescription(request.description());
        existingProduct.setPrice(request.price());
        existingProduct.setCategory(request.category());

        Product updatedProduct = productRepository.save(existingProduct);

        return ProductResponse.from(updatedProduct);
    }

    // Delete product
    public void deleteProduct(Long id) {

        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found");
        }

        productRepository.deleteById(id);
    }
}
