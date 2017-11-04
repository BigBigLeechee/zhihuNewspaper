package com.example.billy.zhihunewspaper_reset.model.bean.ThemeDetail;

import java.util.List;

/**
 * Created by Billy on 2017/11/2.
 */

public class Stories {

    private int type;
    private int id;
    private String title;
    private List<String> images;

    public int getType() {
        return type;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
