package com.salah.book.user;

import java.security.Principal;

public class CustomPrincipal implements Principal {
    private final User user;

    public CustomPrincipal(User user) {
        this.user = user;
    }
    @Override
    public String getName() {
        return user.getEmail();
    }
}
