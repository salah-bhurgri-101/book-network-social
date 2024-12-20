package com.salah.book.user;

import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserResponse toUserReponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .accountLocked(user.isAccountLocked())
                .dateOfBrith(user.getDateOfBrith())
                .enabled(user.isEnabled())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .build();
    }
}
