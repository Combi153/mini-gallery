package huchu.post.dto;

import huchu.post.domain.Post;

public record PostResponse(String title, String url) {

    public static PostResponse from(Post post) {
        return new PostResponse(post.postName(), post.postImageUrl());
    }
}
