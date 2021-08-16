package com.example.dolphin.model;

public class Post {
    //private Bitmap image;
    private String image_url;
    private String description;
    private String supporting_url;
    private String caption;

    public Post(String image_url, String supporting_url,String caption,String description) {
        this.image_url = image_url;
        this.caption = caption;
        this.description = description;
        this.supporting_url = supporting_url;

    }

    public String getImage_url() {
        return image_url;
    }

    public String getImage() {
        return image_url;
    }

    public String getCaption() {
        return caption;
    }

    public String getDescription() {
        return description;
    }

    public String getSupporting_url() {
        return supporting_url;
    }
}
