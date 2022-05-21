package com.qa.client;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class RestClient {

    //1.GET method
    public CloseableHttpResponse get (String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url); //http get request
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget);//hit the get url

    return closeableHttpResponse;
    }
}
