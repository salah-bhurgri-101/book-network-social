package com.salah.book.contact;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ContactRequest(
        Integer id,
        @NotNull(message = "100")
        @NotEmpty(message = "100")
        String name,
        @NotNull(message = "101")
        @NotEmpty(message = "101")
        String secondName,
        @NotNull(message = "102")
        @NotEmpty(message = "102")
        String work,
        @NotNull(message = "103")
        @NotEmpty(message = "103")
        String email,
        @NotNull(message = "104")
        @NotEmpty(message = "104")
        String phone,
        String description

) {
}
