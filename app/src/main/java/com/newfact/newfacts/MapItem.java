package com.newfact.newfacts;

public class MapItem {
    String cafe_name;
    String distance;
    String rating;
    String assign;
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

    public MapItem(String cafe_name, String distance, int resId, String assign){
        this.cafe_name = cafe_name;
        this.distance = distance;
        this.resId = resId;
        this.assign = assign;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getAssign() {
        return assign;
    }

    public void setAssign(String assign) {
        this.assign = assign;
    }
}
