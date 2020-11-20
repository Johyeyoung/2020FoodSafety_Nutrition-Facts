package com.newfact.newfacts.Category;

public class CategoryGridMenuItem {
    public CategoryGridMenuItem(String title, int vectorId) {
        this.title = title;
        this.vectorId = vectorId;
    }

    String title;
    int vectorId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVectorId(int vectorId) {
        this.vectorId = vectorId;
    }

    public int getVectorId() {
        return vectorId;
    }
}
