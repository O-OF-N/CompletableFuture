package com.model;

import java.util.List;

/**
 * Created by vm033450 on 8/20/18.
 */
public class UserPosts {

    private final User user;
    private final List<Post> posts;

    public UserPosts(User user, List<Post> posts) {
        this.user = user;
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "user = " + this.user + " \n" + "post = " + posts + " \n \n";
    }
}
