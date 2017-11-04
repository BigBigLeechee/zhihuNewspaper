package com.example.billy.zhihunewspaper_reset.model.bean.News_latest;

import java.util.List;

/**
 * Created by Billy on 2017/4/16.
 *
 *
 * 最新消息中的头条内容消息
 *
 * stories: [
 * {
 * title: "中国古代家具发展到今天有两个高峰，一个两宋一个明末（多图）",
 * ga_prefix: "052321",
 * images: [
 * "http://p1.zhimg.com/45/b9/45b9f057fc1957ed2c946814342c0f02.jpg"
 *　],
 *　type: 0,
 *　id: 3930445
 * }
 */

public class Stories {


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



    /**
     *
     * get 与 set 函数
     */

    public String getMultipic() {
        return multipic;
    }

    public void setMultipic(String multipic) {
        this.multipic = multipic;
    }
    
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


}
