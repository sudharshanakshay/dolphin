package com.example.dolphin.model;

import java.net.URL;

public class Post {
    //private Bitmap image;
    private URL image_url;
    private String description;
    private String supporting_url;
    private String caption;

    public Post(URL image_url, String supporting_url,String caption,String description) {
        this.image_url = image_url;
        this.caption = caption;
        this.description = description;
        this.supporting_url = supporting_url;

    }

    public URL getImage_url() {
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
