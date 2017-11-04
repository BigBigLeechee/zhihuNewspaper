package com.example.billy.zhihunewspaper_reset.model.bean.ThemeDetail;

import java.util.List;

/**
 * Created by Billy on 2017/11/2.
 */

public class ThemeDetail {

    private List<Stories> stories;
    private String description;
    private String background;
    private int color;
    private String name;
    private String image;
    private  List<Editors> editors;
    private String image_source;

    public List<Stories> getStories() {
        return stories;
    }

    public void setStories(List<Stories> stories) {
        this.stories = stories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Editors> getEditors() {
        return editors;
    }

    public void setEditors(List<Editors> editors) {
        this.editors = editors;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }
}

