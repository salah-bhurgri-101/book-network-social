package com.salah.book.comment;

import com.salah.book.post.Post;
import org.springframework.stereotype.Service;

@Service
public class CommentMapper {
    public Comment toComment(CommmentRequest request) {
        return Comment.builder()
                .content(request.content())
                .post(Post.builder()
                        .id(request.postId())
                        .build())
                .build();
    }
}
