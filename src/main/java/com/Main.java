package com;

import com.completableFuture.HttpCompletableFuture;
import com.future.HttpFuture;
import com.http.HttpResponse;

public class Main {

    public static void main(String[] args) {

        HttpResponse future = new HttpFuture();
        future.printUserPosts();

        HttpResponse completable = new HttpCompletableFuture();
        completable.printUserPosts();

    }
}
