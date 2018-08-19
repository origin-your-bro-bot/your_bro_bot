package com.yourbro.cat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CatService {
    private String url;

    public InputStream getCuteCatPhotoAsStream() throws IOException {
        URL catPictureURL = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) catPictureURL.openConnection();
        connection.setRequestMethod("GET");

        return connection.getInputStream();
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
