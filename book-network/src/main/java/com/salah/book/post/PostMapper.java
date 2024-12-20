package com.salah.book.post;

import com.salah.book.file.FileUtils;
import org.springframework.stereotype.Service;

@Service
public class PostMapper {
    public Post toPost(PostRequest postRequest) {
        return Post.builder()
                .id(postRequest.id())
                .title(postRequest.title())
                .content(postRequest.content())
                .build();
    }

    public PostResponse toPostResponse(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .imageName(FileUtils.readFileFromLocation(post.getImageName()))
                .user(post.getUser().fullName())
                .build();
    }

    public Post toPostByResponse(PostResponse postResponse) {
        return Post.builder()
                .id(postResponse.getId())
                .title(postResponse.getTitle())
                .content(postResponse.getContent())
//                .imageName(FileUtils.readFileFromLocation(postResponse))
//                .user(postResponse.getUser())
                .build();
    }
}
