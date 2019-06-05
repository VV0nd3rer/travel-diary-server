package com.kverchi.diary.hateoas.assembler;

import com.kverchi.diary.controller.PostController;
import com.kverchi.diary.hateoas.resource.PostResource;
import com.kverchi.diary.model.entity.Post;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by Liudmyla Melnychuk on 4.6.2019.
 */
public class PostResourceAssembler extends ResourceAssemblerSupport<Post, PostResource> {
    public PostResourceAssembler() {
        super(PostController.class, PostResource.class);
    }
    @Override
    protected PostResource instantiateResource(Post post) {
        return new PostResource(post);
    }
    @Override
    public PostResource toResource(Post post) {
        return createResourceWithId(post.getPostId(), post);
    }
}
