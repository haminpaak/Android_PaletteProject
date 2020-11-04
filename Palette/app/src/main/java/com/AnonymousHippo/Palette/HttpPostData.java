package com.AnonymousHippo.Palette;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpPostData {
    private static String URLString = "http://";

    public static String POST(String[] keys, String[] datas) {
        try {
            URL url = new URL(URLString);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setDefaultUseCaches(false);
            http.setDoInput(true);
            http.setDoOutput(true);
            http.setRequestMethod("POST");
            http.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), StandardCharsets.UTF_8);
            PrintWriter writer = new PrintWriter(outStream);
            StringBuilder buffer = new StringBuilder();
            buffer.append(keys[0]).append("=").append(datas[0]);

            for (int i = 1; i < keys.length; i++){
                buffer.append("&");
                buffer.append(keys[i]).append("=").append(datas[i]);
            }

            writer.write(buffer.toString());
            writer.flush();

            // 서버에서 전송받기
            InputStreamReader tmp = new InputStreamReader(http.getInputStream(), StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(tmp);
            StringBuilder builder = new StringBuilder();

            try {
                builder.append(reader.read());
            } catch (Exception e) {
                e.printStackTrace();
                return "NO_DATA_RECEIVED";
            }

            return builder.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "SEND_FAIL";
        }
    }

    public void setURL(String url){
        this.URLString = url;
    }

    public String getURL(){
        return this.URLString;
    }
}
