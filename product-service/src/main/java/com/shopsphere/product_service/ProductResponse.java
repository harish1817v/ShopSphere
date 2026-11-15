
package com.shopsphere.product_service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponse(

    Long id,
    String name,
    String sku,
    String description,
    BigDecimal price,
    String category,
    boolean active,
    LocalDateTime createdAt,
    LocalDateTime updatedAt

) {

    public static ProductResponse from(Product product) {

        return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getSku(),
            product.getDescription(),
            product.getPrice(),
            product.getCategory(),
            product.isActive(),
            product.getCreatedAt(),
            product.getUpdatedAt()
        );
    }
}

