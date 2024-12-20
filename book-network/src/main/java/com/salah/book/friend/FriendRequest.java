package com.salah.book.friend;

import com.salah.book.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.pl.NIP;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User receiver;

    @Enumerated(EnumType.STRING)
    private FriendRequestStatus status;

    public enum FriendRequestStatus {
        PENDING, ACCEPTED, DECLINED
    }

    // Getters, Setters, Constructors
}
