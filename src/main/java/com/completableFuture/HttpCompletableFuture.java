package com.completableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.asynchttpclient.Response;

import com.http.HttpResponse;
import com.http.HttpUtil;
import com.model.User;
import com.model.UserPosts;

public class HttpCompletableFuture extends HttpResponse {

    private BiFunction<User, Response, UserPosts> getUserPosts = (user, response) -> new UserPosts(user, super.convertResponseToPost(response.getResponseBody()));

    private Function<User, Function<Response, UserPosts>> userPostMapper = (user -> (response) -> getUserPosts.apply(user, response));

    private Function<List<User>, List<CompletableFuture<UserPosts>>> getPostResponseForUser = users -> users.stream().map(user -> {
        Function<Response, UserPosts> buildUserPosts = userPostMapper.apply(user);
        return super.getPostsForUser(user.getId()).toCompletableFuture().thenApply(buildUserPosts);
    }).collect(Collectors.toList());

    public HttpCompletableFuture() {
        super(HttpUtil.getInstance());
    }

    public void printUserPosts() {

        try {
            System.out.println("--------Completable Future Method Start---------- ");

            List<CompletableFuture<UserPosts>> userPostsCompletable = super.getUsers()
                    .toCompletableFuture()
                    .thenApply(u -> super.convertResponseToUser(u.getResponseBody()))
                    .thenApply(getPostResponseForUser)
                    .join();
            List<UserPosts> userPosts = userPostsCompletable.stream().map(CompletableFuture::join).collect(Collectors.toList());
            System.out.println(userPosts);
            System.out.println("--------Completable Future Method End---------- ");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
