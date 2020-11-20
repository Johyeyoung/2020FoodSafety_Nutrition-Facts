package com.newfact.newfacts.menu;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;


public class Product implements Parcelable{
    private String franchise;
    private String name;
    private String eng;
    private String category;
    private String desc;
    private String pic;
    private String nutrition;
    private String allergy;
    private String volume;
    private String key;

    public Product(String eng, String allergy, String category, String desc, String franchise,
                   String name, String nutrition, String pic, String volume) {
        this.franchise = franchise;
        this.name = name;
        this.eng = eng;
        this.category = category;
        this.desc = desc;
        this.pic = pic;
        this.nutrition = nutrition;
        this.allergy = allergy;
        this.volume = volume;
    }

    public Product(){}

    public Product(String franchise, String name, String pic){
        this.franchise = franchise;
        this.name = name;
        this.pic = pic;
    }

    public Product(Parcel in) {
        franchise = in.readString();
        name = in.readString();
        eng = in.readString();
        category = in.readString();
        desc = in.readString();
        pic = in.readString();
        nutrition = in.readString();
        allergy = in.readString();
        volume = in.readString();
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
    public String getFranchise() {
        return franchise;
    }
    public String getName() {
        return name;
    }
    public String getDesc() {
        return desc;
    }
    public String getVolume() {
        return volume;
    }
    public String getAllergy() {
        return allergy;
    }
    public String getPic() {
        return pic;
    }
    public String getNutrition() {
        return nutrition;
    }



    @Override
    public String toString() {
        return  franchise + "-" + name +"-"+ eng +"-" + category + "-"+
                desc + "-" + nutrition +"-" + allergy + "-" + pic + "-" + volume;
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
        dest.writeString(desc);
        dest.writeString(key);
    }
}