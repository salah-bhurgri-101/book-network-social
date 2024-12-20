package com.salah.book.contact;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactResponse {

    private Integer id;
    private String name;
    private String secondName;
    private String work;
    private String email;
    private String phone;
    private byte[] image;
    private String description;


}
