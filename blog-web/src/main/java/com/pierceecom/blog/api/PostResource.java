package com.pierceecom.blog.api;

import com.pierceecom.blog.model.Post;
import com.pierceecom.blog.service.PostService;
import com.pierceecom.blog.service.PostServiceImpl;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@Path("posts")
public class PostResource {

    PostService postService;

    public PostResource() {

        postService = new PostServiceImpl();

    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPosts() {

        List<Post> postList = postService.getAllPosts();

        return Response
                .status(Response.Status.OK)
                .entity(postList)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPost(@Valid Post post, @Context UriInfo uriInfo) {

        postService.addPost(post);

        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(post.getId());

        return Response
                .created(builder.build())
                .status(Response.Status.CREATED)
                .build();
    }

    @PUT
    public Response updatePost(@Valid Post postToUpdate, @Context UriInfo uriInfo) {

        String status;

        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(postToUpdate.getId());


        status = postService.updatePost(postToUpdate);

        System.out.println(status);

        if (status == "UPDATED") {
            return Response
                    .created(builder.build())
                    .status(Response.Status.CREATED)
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();

        }

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPostById(@PathParam("id") String id) {

        Post post = postService.getPostById(id);


        if (post == null) {
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
    public Response deletePost(@PathParam("id") String id) {

        String status;

        status = postService.deletePost(id);

        if (status == "DELETED") {
            return Response
                    .status(Response.Status.OK)
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }


}
