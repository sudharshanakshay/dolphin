package com.example.dolphin.constants;

public class Constants {
    private String ip = "http://d277fcd0401b.ngrok.io";
    private String signing_url = ip + "/signin.php";
    private String signup_url = ip + "/signup.php";
    private String feed_url = ip + "/feed.php";

    public String getSigning_url() {
        return signing_url;
    }

    public String getSignup_url() {
        return signup_url;
    }

    public String getFeed_url() {
        return feed_url;
    }
}
