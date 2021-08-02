package com.example.dolphin.Auth;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Authenticate {

    public class SignUp implements  Runnable{
        private final String username;
        private final String name;
        private final String email;
        private final String password;
        URL signUp_url;

        public SignUp(String username, String name, String email, String password){

            this.username = username;
            this.name = name;
            this.email = email;
            this.password = password;
        }



        @Override
        public void run() {
            try {
                signUp_url = new URL("https://localhost:8000/SignUp.php");

                String data = URLEncoder.encode("name", "UTF-8")
                        + "=" + URLEncoder.encode(name, "UTF-8");
                data += "&" + URLEncoder.encode("username", "UTF-8")
                        + "=" + URLEncoder.encode(username, "UTF-8");
                data += "&" + URLEncoder.encode("email", "UTF-8")
                        + "=" + URLEncoder.encode(email, "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8")
                        + "=" + URLEncoder.encode(password, "UTF-8");



                HttpURLConnection con = (HttpURLConnection) signUp_url.openConnection();
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                wr.write(data);
                if(con.getResponseCode()==200){
                    System.out.println(con.getResponseMessage());
                }
                wr.flush();
                con.disconnect();

//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            StringBuilder stringBuilder = new StringBuilder();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
