package com.newfact.newfacts.menu;

public class UserInfo {


    private static final UserInfo instance = new UserInfo();

    public UserInfo() {
    }

    public static UserInfo getInstance() {
        return instance;
    }

    private String id = null;
    private String sex = null;
    private String age = null;
    private String height = null;
    private String weight = null;

    private String allergy = null;
    private String nutrition= null;

    //    private UserInfo(String id, String sex, String age, String height, String weight, String allergy, String nutrition) {
//        this.id = id;
//        this.sex = sex;
//        this.age = age;
//        this.height = height;
//        this.weight = weight;
//        this.allergy = allergy;
//        this.nutrition = nutrition;
//    }
    public void setId(String id){ this.id = id; }
    public void setSex(String sex){ this.sex = sex; }
    public void setAge(String age){ this.age = age; }
    public void setHeight(String height){ this.height = height; }
    public void setWeight(String weight){ this.weight = weight; }
    public void setAllergy(String allergy){ this.allergy = allergy;}
    public void setNutrition(String nutrition){this.nutrition = nutrition;}


    public String getId(){return this.id;}
    public String getSex(){return this.sex;}
    public String getAge(){return this.age;}
    public String getHeight(){return this.height;}
    public String getWeight(){return this.weight;}
    public String getAllergy(){return this.allergy;}
    public String getNutrition(){return this.nutrition;}

}
