package com.dps0340.packetAnalyzer.Network;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class RestHandler {
    private URL url = null;

    public RestHandler(String uri) throws MalformedURLException {
        this.url = new URL(uri);
    }

    public URLConnection getRequest() throws IOException {
        URLConnection urlConnection = url.openConnection();
        return urlConnection;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}