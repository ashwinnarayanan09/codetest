package com.pierceecom.blog.service;

import com.pierceecom.blog.model.Post;
import com.pierceecom.blog.repository.PostRepository;

import java.util.List;

public class PostServiceImpl implements PostService {

   private PostRepository postRepository;


   public PostServiceImpl(){

       this.postRepository = new PostRepository();

   }

   public List<Post> getAllPosts(){
       return postRepository.getAllPosts();
   }

   public void addPost(Post post){
       postRepository.addPost(post);
   }

   public void updatePost(Post post){

       postRepository.updatePost(post);
   }

   public Post getPostById(String id){
       return postRepository.getPostById(id);
   }

   public void deletePost(String id){

       postRepository.deletePost(id);

   }
}
