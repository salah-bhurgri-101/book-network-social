package com.salah.book.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse getUser(Authentication connectedUser) {
        CustomUserDetail user = (CustomUserDetail) connectedUser.getPrincipal();
        UserResponse userResponse = userMapper.toUserReponse(user.getUser());
//        userRepository.findById()
        return userResponse;
    }

    public UserResponse getUserByUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found :: " + userId));
        UserResponse userReponse = userMapper.toUserReponse(user);
        return userReponse;
    }

    public List<UserResponse> getAllUser(Authentication connectedUser) {
        List<User> all = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : all){
           userResponses.add(userMapper.toUserReponse(user));
        }
        return userResponses;
    }

//    public Integer deleteUser(Integer userId) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found :: " + userId));
//        userRepository.delete(user);
//        return user.getId();
//    }
}
