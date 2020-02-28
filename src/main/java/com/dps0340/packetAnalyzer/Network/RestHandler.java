package com.dps0340.packetAnalyzer.Network;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.StringJoiner;

public class RestHandler {
    private URL url = null;

    public RestHandler(String uri) throws MalformedURLException {
        this.url = new URL(uri);
    }

    public String get() throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.connect();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
        byte[] bytes = bufferedInputStream.readAllBytes();
        return new String(bytes, "UTF-8");
    }

    public String post(Map<String, String> args) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        StringJoiner stringJoiner = new StringJoiner("&");
        for(Map.Entry<String, String> entry : args.entrySet()) {
            stringJoiner.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
                    + URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        byte[] out = stringJoiner.toString().getBytes(StandardCharsets.UTF_8);
        int length = out.length;
        httpURLConnection.setFixedLengthStreamingMode(length);
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        httpURLConnection.connect();
        try(OutputStream outputStream = httpURLConnection.getOutputStream()) {
            outputStream.write(out);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
        byte[] bytes = bufferedInputStream.readAllBytes();
        return new String(bytes, "UTF-8");
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}