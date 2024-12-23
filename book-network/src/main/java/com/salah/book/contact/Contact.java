package com.salah.book.contact;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salah.book.common.BaseEntity;
import com.salah.book.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Contact extends BaseEntity {

    private String name;
    private String secondName;
    private String work;
    private String email;
    @Column(unique = true)
    private String phone;
    @Lob
    private byte[] image;
    @Column(length = 5000)
    private String description;
    @ManyToOne
    @JsonIgnore
    private User user;

}
