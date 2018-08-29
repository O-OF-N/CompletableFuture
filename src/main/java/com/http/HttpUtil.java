package com.http;

import static org.asynchttpclient.Dsl.asyncHttpClient;

import java.util.Arrays;

import org.asynchttpclient.*;
import org.asynchttpclient.util.HttpConstants;

/**
 * Created by vm033450 on 8/20/18.
 */
public final class HttpUtil {
    private static HttpUtil httpUtil = new HttpUtil();
    AsyncHttpClient client = asyncHttpClient();

    private HttpUtil() {

    }

    public synchronized static HttpUtil getInstance() {
        return HttpUtil.httpUtil;
    }

    public ListenableFuture<Response> get(String url) {
        try {
            ListenableFuture<Response> response = client.prepareGet(url).execute();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public ListenableFuture<Response> getWithParams(String url, String key, String value) {
        try {
            Param p = new Param(key, value);
            Request getRequest = new RequestBuilder(HttpConstants.Methods.GET)
                    .setUrl(url)
                    .setQueryParams(Arrays.asList(new Param[] { p }))
                    .build();
            ListenableFuture<Response> response = client.executeRequest(getRequest);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
