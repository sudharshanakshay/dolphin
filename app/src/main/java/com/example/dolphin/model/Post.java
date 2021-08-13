package com.example.dolphin.model;

public class Post {
    private String image;
    private String description;
    private String supporting_url;
    private String caption;

    public Post(/*String image ,*/String supporting_url,String caption,String description) {
        this.image = image;
        this.caption = caption;
        this.description = description;
        this.supporting_url = supporting_url;

    }

    public String getImage() {
        return image;
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
