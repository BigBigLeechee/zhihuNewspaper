package com.example.billy.zhihunewspaper_reset.model.bean.Before;

import java.util.List;

/**
 * Created by Billy on 2017/4/19.
 */

public class BeforeStories {

    //新闻标题
    private String title;

    //供 Google Analytics 使用
    private String ga_prefix;

    //图像地址
    private List<String> images;

    //作用未知
    private int type;

    //url 与 share_url 中最后的数字（应为内容的 id）
    private int id;

    //消息是否包含多张图片（仅出现在包含多图的新闻中）
    private String multipic;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public int getType() {
        return type;
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

    public String getMultipic() {
        return multipic;
    }

    public void setMultipic(String multipic) {
        this.multipic = multipic;
    }
}
