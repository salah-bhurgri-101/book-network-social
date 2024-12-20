package com.salah.book.notification;

import com.salah.book.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User recipient;

    private String message;
    private boolean read = false; // Track whether the notification is read

    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters, Setters, Constructors
}

