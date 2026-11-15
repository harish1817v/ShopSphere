
package com.shopsphere.product_service;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProductRequest(

    @NotBlank(message = "Product name is required")
    @Size(max = 150)
    String name,

    @NotBlank(message = "SKU is required")
    @Size(max = 64)
    String sku,

    @Size(max = 1000)
    String description,

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than zero")
    @Digits(integer = 10, fraction = 2)
    BigDecimal price,

    @NotBlank(message = "Category is required")
    @Size(max = 100)
    String category

) {}

