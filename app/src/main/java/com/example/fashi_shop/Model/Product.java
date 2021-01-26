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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", product_brands_id=" + product_brands_id +
                ", categories_id=" + categories_id +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}