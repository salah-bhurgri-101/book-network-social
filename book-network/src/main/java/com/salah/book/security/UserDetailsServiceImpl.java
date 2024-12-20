package com.salah.book.security;

import com.salah.book.user.CustomUserDetail;
import com.salah.book.user.User;
import com.salah.book.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepositrory;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<User> byEmail = userRepositrory.findByEmail(userEmail);
//        return byEmail.get(); sir work
        return byEmail.map(CustomUserDetail::new).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }
}
