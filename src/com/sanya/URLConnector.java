package com.sanya;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//simple class for establishment URL connection and saving information from URL to buffered reader
public class URLConnector {
    private String url;
    private BufferedReader bReader;

    public URLConnector(String url){
        this.url = url;
    }

    public BufferedReader urlToBuffer() throws IOException {
        URL urlForSite = new URL(url);
        HttpURLConnection urlConnection =(HttpURLConnection) urlForSite.openConnection();
        if (urlConnection.getResponseCode() != 200)
            bReader = null;
        else
            bReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

        return bReader;
    }
}
