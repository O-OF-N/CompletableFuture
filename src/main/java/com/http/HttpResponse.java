package com.http;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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

    protected final Type userType = new TypeToken<List<User>>() {
    }.getType();
    protected final Type postType = new TypeToken<List<Post>>() {
    }.getType();
    private final HttpUtil httpUtil;

    protected HttpResponse(HttpUtil httpUtil) {
        this.httpUtil = httpUtil;
    }

    protected ListenableFuture<Response> getUsers() {
        return this.httpUtil.get(USERS_URL);
    }

    protected ListenableFuture<Response> getPostsForUser(int userId) {
        ListenableFuture<Response> responseFuture = this.httpUtil
                .getWithParams(POSTS_URL, "userId", Integer.toString(userId));
        return responseFuture;
    }

    protected List<User> convertResponseToUser(String userResponse) {
        if (userResponse == null)
            return new ArrayList<>();
        ConvertJsonToType<User> convertUser = new ConvertJsonToType<>();
        List<User> users = convertUser.convert(userResponse, this.userType);
        return users;
    }

    protected List<Post> convertResponseToPost(String postResponse) {
        if (postResponse == null)
            return new ArrayList<>();
        ConvertJsonToType<Post> convertPost = new ConvertJsonToType<>();
        List<Post> posts = convertPost.convert(postResponse, postType);
        return posts;
    }

    public abstract void printUserPosts();
}

class ConvertJsonToType<T> {

    public List<T> convert(String response, Type type) {
        return new Gson().fromJson(response, type);
    }
}