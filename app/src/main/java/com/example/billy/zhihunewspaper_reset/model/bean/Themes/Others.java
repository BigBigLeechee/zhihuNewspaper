package com.example.billy.zhihunewspaper_reset.model.bean.Themes;

/**
 * Created by Billy on 2017/4/19.
 *
 * json的格式
 *
 * "color": 8307764,
 "thumbnail": "http://pic4.zhimg.com/2c38a96e84b5cc8331a901920a87ea71.jpg",
 "description": "内容由知乎用户推荐，海纳主题百万，趣味上天入地",
 "id": 12,
 "name": "用户推荐日报"
 */

public class Others {

    // 颜色，作用未知
    private int color;
    //供显示的图片地址
    private String thumbnail;
    //主题日报的介绍
    private String description;
    //该主题日报的编号
    private int id;
    //供显示的主题日报名称
    private String name;

    /**
     * get set 方法
     */


    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
