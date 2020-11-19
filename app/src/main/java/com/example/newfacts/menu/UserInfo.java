package com.example.newfacts.menu;

import java.util.HashMap;
import java.util.Map;

public class UserInfo {
    public String id = "";
    public String sex = "";
    public String age = "0";
    public String height="0";
    public String weight="0";

    public String allergy;
    public String nutrition;

    public UserInfo(){}

    public UserInfo( String id, String sex, String age, String height, String weight, String allergy, String nutrition){
        this.id = id;
        this.sex = sex;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.allergy = allergy;
        this.nutrition = nutrition;
    }

    public String allergyToString(boolean allergy){
        if(allergy) return "1";
        else return "0";
    }


//    public Map<String, Object> toMap(){
//        HashMap<String, Object> result = new HashMap<>();
//        result.put("id", id);
//        result.put("sex", sex);
//        result.put("age", age);
//        result.put("height", height);
//        result.put("weight", weight);
//        String allergy;
//        allergy = allergyToString(milk_allergy)+"/"+
//                allergyToString(soybean_allergy)+"/"+
//                allergyToString(peach_allergy)+"/"+
//                allergyToString(tomato_allergy)+"/"+
//                allergyToString(squid_allergy)+"/0";
//        result.put("allergy", allergy);
//        return result;
//    }


}