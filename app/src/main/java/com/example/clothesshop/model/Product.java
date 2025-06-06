package com.example.clothesshop.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Product implements Parcelable {
    private Integer productid;
    private String name;
    private String description;
    private Double price;
    private String quantity;
    private String imageUrl;

    public Product(Integer productid, String name, String description, Double price, String quantity, String imageUrl) {
        this.productid = productid;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public Product(Integer productid, String name, Double price) {
        this.productid = productid;
        this.name = name;
        this.price = price;
    }

    protected Product(Parcel in) {
        name = in.readString();
        description = in.readString();
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readDouble();
        }
        quantity = in.readString();
        imageUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeString(description);
        if (price == null) {
            parcel.writeByte((byte) 0); // Write a flag indicating null
        } else {
            parcel.writeByte((byte) 1); // Write a flag indicating not null
            parcel.writeDouble(price);
        }
        parcel.writeString(quantity);
        parcel.writeString(imageUrl);
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }
}
