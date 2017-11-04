package com.example.billy.zhihunewspaper_reset.model.bean.Themes;

import java.util.List;

/**
 * Created by Billy on 2017/4/19.
 *
 * 主题日报的json格式
 */

public class Themes {

    //返回数目之限制（仅为猜测）
    private int limit;
    // 已订阅条目
    private List<String> subscribed;
    //其他条目
    private List<Others> others;

    /**
     * get set 方法
     */
    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<String> getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(List<String> subscribed) {
        this.subscribed = subscribed;
    }

    public List<Others> getOthers() {
        return others;
    }

    public void setOthers(List<Others> others) {
        this.others = others;
    }
}
