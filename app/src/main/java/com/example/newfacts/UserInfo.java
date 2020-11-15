
import java.util.HashMap;
import java.util.Map;

public class UserInfo {
    public String sex = "";
    public String age = "0";
    public String height="0";
    public String weight="0";

    public boolean milk_allergy=false;
    public boolean soybean_allergy=false;
    public boolean peach_allergy=false;
    public boolean tomato_allergy=false;
    public boolean squid_allergy=false;

    public UserInfo(){}

    public UserInfo( String sex, String age, String height, String weight,
                    boolean milk_allergy, boolean soybean_allergy, boolean peach_allergy,
                    boolean tomato_allergy, boolean squid_allergy){

        this.sex = sex;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.milk_allergy = milk_allergy;
        this.soybean_allergy = soybean_allergy;
        this.peach_allergy = peach_allergy;
        this.tomato_allergy = tomato_allergy;
        this.squid_allergy = squid_allergy;
    }


    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("sex", sex);
        result.put("age", age);
        result.put("height", height);
        result.put("weight", weight);
        result.put("milk_allergy", milk_allergy);
        result.put("soybean_allergy", soybean_allergy);
        result.put("peach_allergy", peach_allergy);
        result.put("tomato_allergy", tomato_allergy);
        result.put("squid_allergy", squid_allergy);

        return result;
    }
}
