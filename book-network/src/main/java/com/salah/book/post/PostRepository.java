package com.salah.book.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post , Integer> {

    @Query(
            """
            SELECT post
            FROM Post post
            WHERE post.user.id != :userId
            """
    )
    Page<Post> findAllDisplayablePosts(Pageable pageable, Integer userId);

    @Query(
            """
            SELECT post
            FROM Post post
            WHERE post.user.id = :userId
            """
    )
    Page<Post> findAllByUser(Pageable pageable, Integer userId);
}
