package com.salah.book.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salah.book.common.BaseEntity;
import com.salah.book.post.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment extends BaseEntity {

    private String content;

    @JsonIgnore
    @ManyToOne
    private Post post;

}
