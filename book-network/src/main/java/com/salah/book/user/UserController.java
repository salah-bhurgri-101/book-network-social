package com.salah.book.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "User")
public class UserController {

    private final UserService service;

    @GetMapping("owner")
    public ResponseEntity<UserResponse> getUser(
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.getUser(connectedUser));
    }

    @GetMapping()
    public ResponseEntity<List<UserResponse>> getAllUser(
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.getAllUser(connectedUser));
    }

    @GetMapping("{user-id}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable("user-id") Integer userId
//            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.getUserByUser(userId));
    }

//    @DeleteMapping("{user-id}")
//    public ResponseEntity<Integer> deleteUser(
//            @PathVariable("user-id") Integer userId
//    ){
//        return ResponseEntity.ok(service.deleteUser(userId));
//    }

}
