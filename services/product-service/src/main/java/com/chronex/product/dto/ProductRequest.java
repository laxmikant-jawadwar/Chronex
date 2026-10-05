package com.chronex.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    @NotBlank(message = "Product name is required")
    private String name;

    @NotBlank(message = "Brand name is required")
    private String brand;

    private String description;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    private BigDecimal price;

    private String category;

    private String gender;

    private String imageUrl;

    private String movement;

    private String glassMaterial;

    private String caseMaterial;

    private String caseShape;

    private Double caseDiameter;

    private String dialColor;

    private String strapMaterial;

    private String strapColor;

    private String waterResistance;

    private String warrantyPeriod;
}
