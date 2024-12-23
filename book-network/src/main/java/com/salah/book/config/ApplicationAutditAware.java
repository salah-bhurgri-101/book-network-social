package com.salah.book.config;

import com.salah.book.user.CustomUserDetail;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class ApplicationAutditAware implements AuditorAware<Integer> {
    @Override
    public Optional<Integer> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken){
            return Optional.empty();
        }
//        User userPrincipal = (User) authentication.getPrincipal(); sir work
//        return Optional.ofNullable(userPrincipal.getId());

        //my work
        CustomUserDetail userPrincipal = (CustomUserDetail) authentication.getPrincipal();

        return Optional.ofNullable(userPrincipal.getUser().getId());
    }
}
