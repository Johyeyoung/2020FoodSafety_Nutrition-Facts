package com.newfact.newfacts.Category;

public class CategoryThemeItem {

    private int imageResoureceID;
    private String description;
    private String title;

    // 테마를 구현하기 위한 데이터 정의
    public CategoryThemeItem(int imageResoureceID, String description, String title){

        this.imageResoureceID = imageResoureceID;
        this.description = description;
        this.title = title;
    }

    public int getImageResoureceID() {
        return imageResoureceID;
    }

    public void setImageResoureceID(int imageResoureceID) {
        this.imageResoureceID = imageResoureceID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
