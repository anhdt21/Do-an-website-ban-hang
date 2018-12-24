package com.cnpm.doanwebbanhang.model;

import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Validated
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty(message = "không được để trống")
    private String name;

    private String image;

    @Column(name = "image", nullable = false)
    public String getImage() {
        return image;
    }

    @NotEmpty(message = "không được để trống")
    private String hot;

    @NotEmpty(message = "không được để trống")
    private String size;

    @NotEmpty(message = "không được để trống")
    private String detail;

    @NotEmpty(message = "không được để trống")
    private String unitPrice;

    @NotEmpty(message = "không được để trống")
    private String promotionPrice;

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @ManyToOne(targetEntity = Producer.class)
    @JoinColumn(name = "idProducer")
    private Producer producer;

    @ManyToOne(targetEntity = ProductType.class)
    @JoinColumn(name = "idProductType")
    private ProductType productType;

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Product() {

    }

    public Product(@NotEmpty(message = "không được để trống") String name, String image, @NotEmpty(message = "không được để trống") String hot, @NotEmpty(message = "không được để trống") String size, @NotEmpty(message = "không được để trống") String detail, @NotEmpty(message = "không được để trống") String unitPrice, @NotEmpty(message = "không được để trống") String promotionPrice, Producer producer, ProductType productType) {
        this.name = name;
        this.image = image;
        this.hot = hot;
        this.size = size;
        this.detail = detail;
        this.unitPrice = unitPrice;
        this.promotionPrice = promotionPrice;
        this.producer = producer;
        this.productType = productType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(String promotionPrice) {
        this.promotionPrice = promotionPrice;
    }


}
