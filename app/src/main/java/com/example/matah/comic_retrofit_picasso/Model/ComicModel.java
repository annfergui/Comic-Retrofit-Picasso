package com.example.matah.comic_retrofit_picasso.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by matah on 11/06/2017.
 */

public class ComicModel {
    //ANOTACIONES GSON:
    @SerializedName("month")
    @Expose
    private String month;
    @SerializedName("num")
    @Expose
    private String num;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("news")
    @Expose
    private String news;
    @SerializedName("safe_title")
    @Expose
    private String safeTitle;
    @SerializedName("transcript")
    @Expose
    private String transcript;
    @SerializedName("alt")
    @Expose
    private String alt;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("day")
    @Expose
    private String day;

    public String getMonth() {
        return month;
    }

    public String getNum() {
        return num;
    }

    public String getLink() {
        return link;
    }

    public String getYear() {
        return year;
    }

    public String getNews() {
        return news;
    }

    public String getSafeTitle() {
        return safeTitle;
    }

    public String getTranscript() {
        return transcript;
    }

    public String getAlt() {
        return alt;
    }

    public String getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }

    public String getDay() {
        return day;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public void setSafeTitle(String safeTitle) {
        this.safeTitle = safeTitle;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
