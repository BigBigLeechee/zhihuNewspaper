package com.example.billy.zhihunewspaper_reset.model.bean.ThemeDetail;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Billy on 2017/11/4.
 *
 * 用于收藏主编的信息，并非属于Json的内容
 */

public class EditorsMessage implements Serializable {

    final static private long SERIALIZABLE_VERSION_ID = 1L;

    private List<Editors> mEditorList;

    public List<Editors> getEditorList() {
        return mEditorList;
    }

    public void setEditorList(List<Editors> mEditorList) {
        this.mEditorList = mEditorList;
    }
}
