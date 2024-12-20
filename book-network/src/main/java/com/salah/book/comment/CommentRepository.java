package com.salah.book.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment , Integer> {
    @Query("""
            SELECT comment
            FROM Comment comment
            WHERE comment.post.id = :postId
            """)
    List<Comment> findAllByPostId(Integer postId);
}
