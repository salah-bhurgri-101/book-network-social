package com.salah.book.user;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Integer id;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBrith;
    private String email;
//    private String password;
    private boolean accountLocked;
    private boolean enabled;
}
