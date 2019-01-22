package com.cnpm.doanwebbanhang.model;

import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    @NotNull(message = "không được để trống")
    private Integer unitPrice;

    @NotNull(message = "không được để trống")
    private Integer promotionPrice;

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

    @OneToOne(targetEntity = Item.class)
    private Item item;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }


    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Product() {

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

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(Integer promotionPrice) {
        this.promotionPrice = promotionPrice;
    }


}
