package com.salah.book.post;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponse {
    private Integer id;
    private String title;
    private String content;
    private byte[] imageName;
    private String user;

}
