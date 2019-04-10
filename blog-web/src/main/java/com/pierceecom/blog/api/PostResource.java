package com.pierceecom.blog.api;

import com.pierceecom.blog.model.Post;
import com.pierceecom.blog.service.PostServiceImpl;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.Path;
import java.util.List;

@Path("blogs")
public class PostResource {

    private PostServiceImpl postServiceImpl;

    public PostResource(){

        this.postServiceImpl = new PostServiceImpl();
    }

    @GET
    @Path("/posts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPosts() {

        List<Post> postList = postServiceImpl.getAllPosts();

        return Response
                .status(Response.Status.OK)
                .entity(postList)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/posts")
    public Response addPost(Post post,@Context UriInfo uriInfo) {

        this.postServiceImpl.addPost(post);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(post.getId());

        return Response
                .created(builder.build())
                .status(Response.Status.CREATED)
                .build();
    }

    @PUT
    @Path("/posts")
    public Response updatePost(Post post,@Context UriInfo uriInfo) {

        this.postServiceImpl.updatePost(post);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(post.getId());

        return Response
                .created(builder.build())
                .status(Response.Status.CREATED)
                .build();
    }

    @GET
    @Path("/posts/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPostById(@PathParam("id")String id) {
        Post post = postServiceImpl.getPostById(id);

        System.out.println(post.getId());

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
    @Path("/posts/{id}")
    public Response deletePost(@PathParam("id")String id) {

        postServiceImpl.deletePost(id);

        return Response
                .status(Response.Status.OK)
                .build();
    }
}
