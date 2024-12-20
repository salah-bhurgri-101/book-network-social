package com.salah.book.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public Integer addComment(CommmentRequest request, Authentication connectedUser) {
        Comment comment = commentMapper.toComment(request);
        return commentRepository.save(comment).getId();
    }

    public List<Comment> findAllCommentByPostId(Integer postId) {

        return commentRepository.findAllByPostId(postId);
    }

    public Integer deleteComment(Integer commentId) {
        commentRepository.deleteById(commentId);
        return commentId;
    }
}
