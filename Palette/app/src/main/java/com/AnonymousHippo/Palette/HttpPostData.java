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
    private static final String URLString = "http://141.164.40.63:8000/";

    public static String POST(String dir, String[] keys, String[] data) {
        try {
            URL url = new URL(URLString + dir);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setDefaultUseCaches(false);
            http.setDoInput(true);
            http.setDoOutput(true);
            http.setRequestMethod("POST");
            http.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), StandardCharsets.UTF_8);
            PrintWriter writer = new PrintWriter(outStream);
            StringBuilder buffer = new StringBuilder();
            buffer.append(keys[0]).append("=").append(data[0]);

            for (int i = 1; i < keys.length; i++){
                buffer.append("&");
                buffer.append(keys[i]).append("=").append(data[i]);
            }

            Log.i("QuerySet", buffer.toString());

            writer.write(buffer.toString());
            writer.flush();

            // 서버에서 전송받기
            InputStreamReader tmp = new InputStreamReader(http.getInputStream(), StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(tmp);
            StringBuilder builder = new StringBuilder();
            String string;

            while ((string = reader.readLine()) != null) {
                builder.append(string);
            }

             return builder.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "SEND_FAIL";
        }
    }

    public String getURL(){
        return URLString;
    }
}
