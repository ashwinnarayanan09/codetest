package com.pierceecom.blog;

import com.pierceecom.blog.api.PostResource;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
public class JAXRSConfiguration  extends Application {


    @Override
    public Set<Object> getSingletons() {
        Set<Object> set = new HashSet<>();
        set.add(new PostResource());
        return set;
    }
}
