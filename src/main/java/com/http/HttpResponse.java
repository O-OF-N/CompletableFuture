package com.http;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import org.asynchttpclient.ListenableFuture;
import org.asynchttpclient.Response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.model.Post;
import com.model.User;

/**
 * Created by vm033450 on 8/20/18.
 */
public abstract class HttpResponse {

    private static final String POSTS_URL = "http://jsonplaceholder.typicode.com/posts";
    private static final String USERS_URL = "http://jsonplaceholder.typicode.com/users";
    private final HttpUtil httpUtil;
    private final BiFunction<String, Type, List> convert = (response, type) -> new Gson().fromJson(response, type);

    protected HttpResponse(HttpUtil httpUtil) {
        this.httpUtil = httpUtil;
    }

    protected ListenableFuture<Response> getUsers() {
        return this.httpUtil.get(USERS_URL);
    }

    protected ListenableFuture<Response> getPostsForUser(int userId) {
        return this.httpUtil.getWithParams(POSTS_URL, "userId", Integer.toString(userId));
    }

    protected List<User> convertResponseToUser(String userResponse) {
        if (userResponse == null)
            return new ArrayList<>();
        return convert.apply(userResponse, new TypeToken<List<User>>() {
        }.getType());
    }

    protected List<Post> convertResponseToPost(String postResponse) {
        if (postResponse == null)
            return new ArrayList<>();
        return convert.apply(postResponse, new TypeToken<List<Post>>() {
        }.getType());
    }

    public abstract void printUserPosts();
}
