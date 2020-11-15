package com.newfact.newfacts;

public class UserInfo {
    public String sex = "";
    public int age = 0;
    public int weight=0;
    public int height=0;

    public boolean milk_allergy=false;
    public boolean soybean_allergy=false;
    public boolean peach_allergy=false;
    public boolean tomato_allergy=false;
    public boolean squid_allergy=false;

    public UserInfo(){}

    public UserInfo( String sex, int age, int height, int weight,
                    boolean milk_allergy, boolean soybean_allergy, boolean peach_allergy,
                    boolean tomato_allergy, boolean squid_allergy){
        this.sex = sex;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.milk_allergy = milk_allergy;
        this.soybean_allergy = soybean_allergy;
        this.peach_allergy = peach_allergy;
        this.tomato_allergy = tomato_allergy;
        this.squid_allergy = squid_allergy;
    }
}
