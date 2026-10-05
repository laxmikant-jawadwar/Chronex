package com.chronex.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Brand name is required")
    @Column(nullable = false)
    private String brand;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    @Column(nullable = false)
    private BigDecimal price;

    private String category;

    private String gender;

    @Column(name = "image_url")
    private String imageUrl;

    private String movement;

    @Column(name = "glass_material")
    private String glassMaterial;

    @Column(name = "case_material")
    private String caseMaterial;

    @Column(name = "case_shape")
    private String caseShape;

    @Column(name = "case_diameter")
    private Double caseDiameter;

    @Column(name = "dial_color")
    private String dialColor;

    @Column(name = "strap_material")
    private String strapMaterial;

    @Column(name = "strap_color")
    private String strapColor;

    @Column(name = "water_resistance")
    private String waterResistance;

    @Column(name = "warranty_period")
    private String warrantyPeriod;
}
