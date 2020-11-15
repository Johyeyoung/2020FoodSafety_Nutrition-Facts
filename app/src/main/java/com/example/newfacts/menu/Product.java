package com.example.newfacts.menu;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;


public class Product implements Parcelable{
    private String franchise;
    private String name;
    private String category;
    private String desc;
    private String pic;
    private String nutrition;
    private String alergy;
    private String key;
    public Product(int caffeine, String category, String desc, float fat, String franchise, int kcal,
                   int milk_aliergy, String name, int octopus_aliergy, int peach_aliergy, String pic, int protein,
                   int sodidum, int soy_aliergy, int sugar, int tomato_aliergy, String volume) {
        this.franchise = franchise;
        this.name = name;
        this.category = category;
        this.desc = desc;
        this.pic = pic;
        this.nutrition = name;  // 변경 요망
        this.alergy = name;  // 변경 요망


    }
    public Product(){}

    public Product(Parcel in) {
        franchise = in.readString();
        name = in.readString();
        category = in.readString();
        key = in.readString();
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFranchise() {
        return franchise;
    }

    public void setFranchise(String franchise) {
        this.franchise = franchise;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "franchise='" + franchise + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("franchise", franchise);
        result.put("name", name);
        result.put("category", category);
        return result;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(franchise);
        dest.writeString(name);
        dest.writeString(category);
        dest.writeString(key);
    }
}