package com.example.newfacts;

public class MapItem {
    String cafe_name;
    String distance;
    int resId;

    public MapItem(String cafe_name, String distance){
        this.cafe_name = cafe_name;
        this.distance = distance;
    }

    public MapItem(String cafe_name, String distance, int resId){
        this.cafe_name = cafe_name;
        this.distance = distance;
        this.resId = resId;
    }

    public String getCafe_name() {
        return cafe_name;
    }

    public void setCafe_name(String cafe_name) {
        this.cafe_name = cafe_name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
