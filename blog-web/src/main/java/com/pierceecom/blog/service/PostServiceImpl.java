package com.pierceecom.blog.service;

import com.pierceecom.blog.model.Post;
import com.pierceecom.blog.repository.PostRepository;

import java.util.List;

public class PostServiceImpl implements PostService{

    private PostRepository postRepository;

    public PostServiceImpl(){

        postRepository = new PostRepository();
    }

    public List<Post> getAllPosts(){

        return postRepository.getAllPosts();
    }

    public void addPost(Post post){

        postRepository.addPost(post);

    }

    public String updatePost(Post post){

        return postRepository.updatePost(post);

    }

    public Post getPostById(String id){

        return postRepository.getPostById(id);
    }

    public String deletePost(String id){

        return postRepository.deletePost(id);
    }
}
