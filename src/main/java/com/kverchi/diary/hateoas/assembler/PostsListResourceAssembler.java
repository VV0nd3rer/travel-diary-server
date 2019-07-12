package com.kverchi.diary.hateoas.assembler;

import com.kverchi.diary.controller.PostController;
import com.kverchi.diary.hateoas.resource.PostsListResource;
import com.kverchi.diary.model.entity.Post;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by Liudmyla Melnychuk on 4.6.2019.
 */
public class PostsListResourceAssembler extends ResourceAssemblerSupport<Post, PostsListResource> {
    public PostsListResourceAssembler() {
        super(PostController.class, PostsListResource.class);
    }
    @Override
    protected PostsListResource instantiateResource(Post post) {
        return new PostsListResource(post);
    }
    @Override
    public PostsListResource toResource(Post post) {
        return createResourceWithId(post.getPostId(), post);
    }
}
