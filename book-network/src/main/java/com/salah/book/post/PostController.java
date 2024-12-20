package com.salah.book.post;

import com.salah.book.common.PageResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("posts")
@RequiredArgsConstructor
@Tag(name = "Post")
public class PostController {

    private final PostService service;

    @PostMapping
    public ResponseEntity<Integer> savePost(
            @Valid @RequestBody PostRequest postRequest,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(service.createPost(postRequest, connectedUser));
    }

    @PostMapping(value = "/file/{post-id}", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadPost(
            @PathVariable("post-id") Integer postId,
            @Parameter
            @RequestPart("file") MultipartFile file,
            Authentication connectedUser
    ) {
        service.uploadPost(file, postId, connectedUser);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("{post-id}")
    public ResponseEntity<PostResponse> findPostById(
            @PathVariable("post-id") Integer postId
    ){
        return ResponseEntity.ok(service.findPostById(postId));
    }

    @GetMapping
    public ResponseEntity<PageResponse<PostResponse>> findAllPosts(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.findAllPosts(page, size ,connectedUser));
    }

    @GetMapping("/user")
    public ResponseEntity<PageResponse<PostResponse>> findAllByUser(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.findAllByUserId(page, size, connectedUser));
    }

    @DeleteMapping("{post-id}")
    public ResponseEntity<Integer> deletePost(
            @PathVariable("post-id") Integer postId
    ){
    return ResponseEntity.ok(service.deletePost(postId));
    }

}
