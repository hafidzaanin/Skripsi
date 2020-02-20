package com.example.trashme;

public class TipsViewModel {
    private int imageTips;
    private String title, desc;

    public TipsViewModel(int imageTips, String title, String desc) {
        this.imageTips = imageTips;
        this.title = title;
        this.desc = desc;
    }

    public int getImageTips() {
        return imageTips;
    }

    public void setImageTips(int imageTips) {
        this.imageTips = imageTips;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
