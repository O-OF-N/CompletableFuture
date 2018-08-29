package com;

import com.completableFuture.HttpCompletableFuture;
import com.future.HttpFuture;
import com.http.HttpResponse;

/**
 * Runner
 */
public class Main {

    public static void main(String[] args) {

        // Future
        HttpResponse future = new HttpFuture();
        future.printUserPosts();

        //Completable Future.
        HttpResponse completable = new HttpCompletableFuture();
        completable.printUserPosts();

    }
}
