package org.teamblueridge.status;

import android.graphics.drawable.Drawable;

public class DrawerItem {

    private boolean mShowNotify;
    private Drawable mIcon;
    private String mTitle;

    public DrawerItem() {
    }

    public DrawerItem(boolean showNotify, Drawable icon, String title) {
        mShowNotify = showNotify;
        mIcon = icon;
        mTitle = title;
    }

    public boolean isShowNotify() {
        return mShowNotify;
    }

    public void setShowNotify(boolean showNotify) {
        mShowNotify = showNotify;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public void setIcon(Drawable icon) {
        mIcon = icon;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
