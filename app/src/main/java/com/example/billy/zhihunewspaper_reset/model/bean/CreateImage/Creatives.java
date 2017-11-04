package com.example.billy.zhihunewspaper_reset.model.bean.CreateImage;


import java.util.List;

/**
 * Created by Billy on 2017/4/20.
 *
 * 开启界面时候的图片json格式
 */

public class Creatives {

    // 图像的 URL
    private String url;
    //获取的时间(ms)
    private int start_time;
    private List<Impression_tracks> impression_tracks;
    private int type;
    private String id;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStart_time() {
        return start_time;
    }

    public List<Impression_tracks> getImpression_tracks() {
        return impression_tracks;
    }

    public void setImpression_tracks(List<Impression_tracks> impression_tracks) {
        this.impression_tracks = impression_tracks;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
