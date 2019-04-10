package com.pierceecom.blog.api;

import com.pierceecom.blog.model.Post;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@Path("posts")
public class PostResource {

    List<Post> postList;

    public PostResource(){
         this.postList = new ArrayList<>();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPosts() {

        return Response
                .status(Response.Status.OK)
                .entity(postList)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPost(@Valid Post post,@Context UriInfo uriInfo) {

        postList.add(createPost(post.getId(),post.getTitle(),post.getContent()));

        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(post.getId());

        return Response
                .created(builder.build())
                .status(Response.Status.CREATED)
                .build();
    }

    @PUT
    public Response updatePost(@Valid Post postToUpdate, @Context UriInfo uriInfo) {

        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(postToUpdate.getId());

        for (Post post : postList) {
            if (post.getId().equals(postToUpdate.getId())) {
                post.setContent(postToUpdate.getContent());
                return Response
                        .created(builder.build())
                        .status(Response.Status.CREATED)
                        .build();
            }
        }

        return Response
                .status(Response.Status.NOT_FOUND)
                .build();

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPostById(@PathParam("id")String id) {
        Post post = postList.stream()
                .filter(post1 -> id.equals(post1.getId())).findAny().orElse(null);

        if(post == null){
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(post)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePost(@PathParam("id")String id) {

        //postList.removeIf(post -> post.getId().equals(id));
        for (Post post : postList) {
            if (post.getId().equals(id)) {
                postList.remove(post);
                return Response
                        .status(Response.Status.OK)
                        .build();
            }
        }

        return Response
                .status(Response.Status.NOT_FOUND)
                .build();
    }

    public Post createPost(String id,String title,String content){

        Post post = new Post();

        post.setId(id);
        post.setTitle(title);
        post.setContent(content);

        return post;

    }
}
