package com.salah.book.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class RegistrationRequest {

    @NotEmpty(message = "Firstname is mandatory")
    @NotNull(message = "First is not Null")
    private String firstname;
    @NotEmpty(message = "Lastname is mandatory")
    @NotNull(message = "Last is not Null")
    private String lastname;
    @Email(message = "Email is not be formatted")
    @NotEmpty(message = "Email is mandatory")
    @NotNull(message = "Email is not Null")
    private String email;
    @NotEmpty(message = "Password is mandatory")
    @NotNull(message = "Password is not Null")
    @Size(min = 8 , message = "Password should be 8 Characters long minimuns")
    private String password;

}
