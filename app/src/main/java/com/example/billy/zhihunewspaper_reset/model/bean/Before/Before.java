package com.example.billy.zhihunewspaper_reset.model.bean.Before;


import com.example.billy.zhihunewspaper_reset.model.bean.News_latest.Stories;

import java.util.List;

/**
 * Created by Billy on 2017/4/19.
 *
 *
 * 历史新闻的jsn
 */

public class Before {

    private String date;         //日期
    private List<Stories> stories;    //当日新闻

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Stories> getStories() {
        return stories;
    }

    public void setStories(List<Stories> stories) {
        this.stories = stories;
    }
}
