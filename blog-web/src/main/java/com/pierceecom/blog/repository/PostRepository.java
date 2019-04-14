package com.pierceecom.blog.repository;

import com.pierceecom.blog.model.Post;
import java.util.ArrayList;
import java.util.List;

public class PostRepository {

    List<Post> postList;

    public PostRepository(){
        this.postList = new ArrayList<>();
    }

    public List<Post> getAllPosts(){

        return postList;
    }

    public void addPost(Post post){

        postList.add(createPost(post.getId(),post.getTitle(),post.getContent()));

    }

    public String updatePost(Post postToUpdate){

        for (Post post : postList) {
            if (post.getId().equals(postToUpdate.getId())) {
                post.setContent(postToUpdate.getContent());
                return "UPDATED";

            }
        }
        return "NOT_FOUND";

    }

    public Post getPostById(String id){

        Post post = postList.stream()
                .filter(post1 -> id.equals(post1.getId())).findAny().orElse(null);

        return post;
    }

    public String deletePost(String id){

        for (Post post : postList) {
            if (post.getId().equals(id)) {
                postList.remove(post);
                return "DELETED";
            }
        }

        return "NOT_FOUND";
    }

    public Post createPost(String id,String title,String content){

        Post post = new Post();

        post.setId(id);
        post.setTitle(title);
        post.setContent(content);

        return post;

    }
}
