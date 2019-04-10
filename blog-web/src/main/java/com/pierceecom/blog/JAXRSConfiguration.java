package com.pierceecom.blog;

import com.pierceecom.blog.api.PostResource;
import com.pierceecom.blog.service.PostService;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
public class JAXRSConfiguration  extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> classes = new HashSet<>();
        classes.add(PostResource.class);
        return classes;
    }
}
