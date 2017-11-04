package com.example.billy.zhihunewspaper_reset.client.fragment.ThemeFragment;

import android.os.Bundle;

import com.example.billy.zhihunewspaper_reset.client.fragment.ThemeFragment.BaseTheme.BaseThemeInterface;
import com.example.billy.zhihunewspaper_reset.client.fragment.ThemeFragment.BaseTheme.ThemeBaseFragment;
import com.example.billy.zhihunewspaper_reset.client.fragment.ThemeFragment.BaseTheme.ThemeID;
import com.example.billy.zhihunewspaper_reset.model.network.URLManager;

/**
 * Created by Billy on 2017/11/4.
 *
 * 互联网安全日报
 */

public class InternetSafeFragment extends ThemeBaseFragment {

    private String url;

    @Override
    protected void initParams(Bundle bundle) {
        url = URLManager.base_theme_url + ThemeID.INTERNET_SAFE_ID;
    }

    @Override
    public BaseThemeInterface initBaseTheme() {
        return new BaseThemeInterface() {
            @Override
            public String getThemeUrl() {
                return url;
            }
        };
    }
}
