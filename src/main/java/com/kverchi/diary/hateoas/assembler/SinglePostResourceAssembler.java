package com.kverchi.diary.hateoas.assembler;

import com.kverchi.diary.controller.PostController;
import com.kverchi.diary.hateoas.resource.SinglePostResource;
import com.kverchi.diary.model.entity.Post;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by Liudmyla Melnychuk on 12.7.2019.
 */
public class SinglePostResourceAssembler extends ResourceAssemblerSupport<Post, SinglePostResource> {
    public SinglePostResourceAssembler() {
        super(PostController.class, SinglePostResource.class);
    }
    @Override
    protected SinglePostResource instantiateResource(Post post) {
        return new SinglePostResource(post);
    }
    @Override
    public SinglePostResource toResource(Post post) {
        return createResourceWithId(post.getPostId(), post);
    }
}
