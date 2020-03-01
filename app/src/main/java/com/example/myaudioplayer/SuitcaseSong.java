package com.example.myaudioplayer;

public class SuitcaseSong {

    private String title;
    private String apath;
    private int imageid;

    public SuitcaseSong() {
    }

    public SuitcaseSong(String title, String apath, int imageid) {
        this.title = title;
        this.apath = apath;
        this.imageid = imageid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getApath() {
        return apath;
    }

    public void setApath(String apath) {
        this.apath = apath;
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    @Override
    public String toString() {
        return "SuitcaseSong{" +
                "title='" + title + '\'' +
                ", apath='" + apath + '\'' +
                ", imageid=" + imageid +
                '}';
    }
}
