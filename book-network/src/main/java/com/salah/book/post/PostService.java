package com.salah.book.post;

import com.salah.book.common.PageResponse;
import com.salah.book.file.FileStorageService;
import com.salah.book.user.CustomUserDetail;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final FileStorageService fileStorageService;

    public Integer createPost(PostRequest postRequest, Authentication connectedUser) {
        CustomUserDetail user = (CustomUserDetail) connectedUser.getPrincipal();
        Post post = postMapper.toPost(postRequest);
        System.err.println(post);
        System.err.println(user.getUser());
        post.setUser(user.getUser());
        return postRepository.save(post).getId();
    }

    public void uploadPost(MultipartFile file, Integer postId, Authentication connectedUser) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("No Post found with Id :: " + postId));
        CustomUserDetail user = (CustomUserDetail) connectedUser.getPrincipal();
        String fileName = fileStorageService.saveFile(file, user.getUser().getId());
        post.setImageName(fileName);
        postRepository.save(post);
    }

    public PostResponse findPostById(Integer postId) {
        return postRepository.findById(postId)
                .map(postMapper::toPostResponse)
                .orElseThrow(()-> new EntityNotFoundException("No post found with id :: "+ postId));
    }

    public PageResponse<PostResponse> findAllPosts(int page, int size, Authentication connectedUser) {
        CustomUserDetail user = (CustomUserDetail) connectedUser.getPrincipal();
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Post> posts = postRepository.findAllDisplayablePosts(pageable , user.getUser().getId());
        List<PostResponse> postRespoonse = posts.stream().map(postMapper::toPostResponse).toList();

        return new PageResponse<>(
                postRespoonse,
                posts.getNumber(),
                posts.getSize(),
                posts.getTotalElements(),
                posts.getTotalPages(),
                posts.isFirst(),
                posts.isLast()
        );
    }

    public PageResponse<PostResponse> findAllByUserId(int page, int size, Authentication connectedUser) {
        CustomUserDetail user = (CustomUserDetail) connectedUser.getPrincipal();
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Post> posts = postRepository.findAllByUser(pageable , user.getUser().getId());
        List<PostResponse> postRespoonse = posts.stream().map(postMapper::toPostResponse).toList();

        return new PageResponse<>(
                postRespoonse,
                posts.getNumber(),
                posts.getSize(),
                posts.getTotalElements(),
                posts.getTotalPages(),
                posts.isFirst(),
                posts.isLast()
        );
    }

    public Integer deletePost(Integer postId) {
        PostResponse postById = findPostById(postId);
        Post post = postMapper.toPostByResponse(postById);
        postRepository.delete(post);
        return post.getId();
    }
}
