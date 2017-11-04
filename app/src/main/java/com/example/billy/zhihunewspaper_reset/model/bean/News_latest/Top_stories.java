package com.example.billy.zhihunewspaper_reset.model.bean.News_latest;

/**
 * Created by Billy on 2017/4/16.
 *
 * 头条消息当中的图片消息
 *
 * top_stories: [
 {
 title: "商场和很多人家里，竹制家具越来越多（多图）",
 image: "http://p2.zhimg.com/9a/15/9a1570bb9e5fa53ae9fb9269a56ee019.jpg",
 ga_prefix: "052315",
 type: 0,
 id: 3930883
 }
 */

public class Top_stories {


    // 新闻标题
    private String title;

    //图像地址
    private String image;

    //供 Google Analytics 使用
    private String ga_prefix;

    //作用未知
    private int type;

    //url 与 share_url 中最后的数字（应为内容的 id）
    private int id;

    /**
     *
     * get 与 set 函数
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
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
}
