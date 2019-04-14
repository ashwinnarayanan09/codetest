package com.pierceecom.blog.service;

import com.pierceecom.blog.model.Post;

import java.util.List;

public interface PostService {

    List<Post> getAllPosts();

    void addPost(Post post);

    String updatePost(Post post);

    Post getPostById(String id);

    String deletePost(String id);
}
