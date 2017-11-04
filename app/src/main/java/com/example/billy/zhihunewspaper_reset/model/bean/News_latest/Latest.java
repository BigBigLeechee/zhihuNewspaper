package com.example.billy.zhihunewspaper_reset.model.bean.News_latest;

import java.util.List;

/**
 * Created by Billy on 2017/4/16.
 *
 *
 * 知乎日报中的最新消息
 *
 *  {
 date: "20140523",
 stories: [
 {
 title: "中国古代家具发展到今天有两个高峰，一个两宋一个明末（多图）",
 ga_prefix: "052321",
 images: [
 "http://p1.zhimg.com/45/b9/45b9f057fc1957ed2c946814342c0f02.jpg"
 ],
 type: 0,
 id: 3930445
 },
 ...
 ],
 top_stories: [
 {
 title: "商场和很多人家里，竹制家具越来越多（多图）",
 image: "http://p2.zhimg.com/9a/15/9a1570bb9e5fa53ae9fb9269a56ee019.jpg",
 ga_prefix: "052315",
 type: 0,
 id: 3930883
 },
 ...
 ]
 }
 */

public class Latest {

    private String date;         //日期
    private List<Stories> stories;    //当日新闻

    //界面顶部 ViewPager 滚动显示的显示内容（子项格式同上）
    //（请注意区分此处的 image 属性与 stories 中的 images 属性）
    private List<Top_stories> top_stories;

    /**
     *
     * get 与 set 函数
     */

    public String getData() {
        return date;
    }

    public void setData(String data) {
        this.date = data;
    }

    public List<Stories> getStories() {
        return stories;
    }

    public void setStories(List<Stories> stories) {
        this.stories = stories;
    }

    public List<Top_stories> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<Top_stories> top_stories) {
        this.top_stories = top_stories;
    }



}
