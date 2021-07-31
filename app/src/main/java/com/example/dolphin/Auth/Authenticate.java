package com.example.dolphin.Auth;

import java.net.MalformedURLException;
import java.net.URL;

public class Authenticate {
    URL url;

    {
        try {
            url = new URL("localhost:8000/SignUp.php");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
