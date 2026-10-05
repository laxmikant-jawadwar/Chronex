package com.chronex.product.dto;

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
public class ProductResponse {

    private Long id;
    private String name;
    private String brand;
    private String description;
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
