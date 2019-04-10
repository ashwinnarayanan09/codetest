package com.pierceecom.blog.service;

import com.pierceecom.blog.model.Post;

import java.util.List;

public interface PostService {

    List<Post> getAllPosts();

    void addPost(Post post);

    void updatePost(Post post);

    Post getPostById(String id);

    void deletePost(String id);
}
