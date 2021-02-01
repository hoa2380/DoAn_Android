package com.example.fashi_shop.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("desc")
    @Expose
    public String desc;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("price")
    @Expose
    public int price;
    @SerializedName("product_brands_id")
    @Expose
    public Integer product_brands_id;
    @SerializedName("categories_id")
    @Expose
    public Integer categories_id;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
   @SerializedName("image_gio_hang")
    @Expose
    public byte[] image_gio_hang;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public byte[] getImage_gio_hang() {
        return image_gio_hang;
    }

    public void setImage_gio_hang(byte[] image_gio_hang) {
        this.image_gio_hang = image_gio_hang;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}