package com.pierceecom.blog.repository;

import com.pierceecom.blog.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostRepository {

    private static List<Post> postList;

    public PostRepository(){

        this.postList = new ArrayList<>();
        postList.add(createPost("1","First title","First content"));
        postList.add(createPost("2","Second title","Second content"));
    }

    public List<Post> getAllPosts(){

        return postList;

    }

    public void addPost(Post post){

        postList.add(createPost(post.getId(),post.getTitle(),post.getContent()));

    }

    public void updatePost(Post postToUpdate){

        for (Post post : postList) {
            if (post.getId().equals(postToUpdate.getId())) {
                post.setContent(postToUpdate.getContent());
                break;
            }
        }

    }

    public Post getPostById(String id){

        return postList.stream()
                .filter(post1 -> id.equals(post1.getId())).findAny().orElse(null);

    }

    public void deletePost(String id){

        postList.removeIf(post -> post.getId().equals(id));

    }

    public Post createPost(String id,String title,String content){

        Post post = new Post();

        post.setId(id);
        post.setTitle(title);
        post.setContent(content);

        return post;

    }
}
