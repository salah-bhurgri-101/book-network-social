package com.salah.book.comment;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comments")
@RequiredArgsConstructor
@Tag(name = "Comment")
public class CommentController {

    private final CommentService service;

    @PostMapping
    public ResponseEntity<Integer> addComment(
            @Valid @RequestBody CommmentRequest request,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.addComment(request , connectedUser));
    }

    @GetMapping("{post-id}")
    public ResponseEntity<List<Comment>> findAllCommentByPostId(
            @PathVariable("post-id") Integer postId
    ){
        return ResponseEntity.ok(service.findAllCommentByPostId(postId));
    }

    @DeleteMapping("{comment-id}")
    public ResponseEntity<Integer> deleteComment(
            @PathVariable("comment-id") Integer commentId
    ){
        return ResponseEntity.ok(service.deleteComment(commentId));
    }

}
