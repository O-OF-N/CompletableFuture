package com.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.asynchttpclient.Response;

import com.http.HttpResponse;
import com.http.HttpUtil;
import com.model.Post;
import com.model.User;
import com.model.UserPosts;

/**
 * Build UserPosts without using CompletableFuture.
 */
public class HttpFuture extends HttpResponse {

    public HttpFuture() {
        super(HttpUtil.getInstance());
    }

    public void printUserPosts() {
        System.out.println("--------Future Method Start---------- ");
        List<UserPosts> userPosts = new ArrayList<>();
        Future<Response> responseFuture = super.getUsers();
        String usersString = null;
        try {
            usersString = responseFuture.get().getResponseBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<User> users = super.convertResponseToUser(usersString);
        if (users == null)
            return;
        for (User user : users) {
            Future<Response> userResponse = super.getPostsForUser(user.getId());
            try {
                String postsString = userResponse.get().getResponseBody();
                List<Post> posts = super.convertResponseToPost(postsString);
                if (posts == null)
                    continue;
                UserPosts u = new UserPosts(user, posts);
                userPosts.add(u);
            } catch (Exception e) {
                continue;
            }
        }
        System.out.println(userPosts);
        System.out.println("--------Future Method End---------- ");
    }

}
