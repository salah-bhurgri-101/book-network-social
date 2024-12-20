package com.salah.book.friend;

import com.salah.book.notification.Notification;
import com.salah.book.notification.NotificationService;
import com.salah.book.user.User;
import com.salah.book.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendRequestService {
    @Autowired
    private FriendRequestRepository friendRequestRepository;
    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserRepository userRepository;

    public FriendRequest sendFriendRequest(String senderUsername, String receiverUsername) {
        User sender = userRepository.findByEmail(senderUsername)
                .orElseThrow(() -> new UsernameNotFoundException("Sender not found"));
        User receiver = userRepository.findByEmail(receiverUsername)
                .orElseThrow(() -> new UsernameNotFoundException("Receiver not found"));

        if (sender.equals(receiver)) {
            throw new IllegalArgumentException("Cannot send a friend request to yourself");
        }

        // Check if they are already friends
        if (sender.getFriends().contains(receiver)) {
            throw new IllegalArgumentException("You are already friends with " + receiverUsername);
        }

        // Check if a request already exists and is pending
        Optional<FriendRequest> existingRequest = friendRequestRepository.findBySenderAndReceiver(sender, receiver);
        if (existingRequest.isPresent() && existingRequest.get().getStatus() == FriendRequest.FriendRequestStatus.PENDING) {
            throw new IllegalArgumentException("Friend request already exists");
        }

        // Create and save friend request
        FriendRequest request = new FriendRequest();
        request.setSender(sender);
        request.setReceiver(receiver);
        request.setStatus(FriendRequest.FriendRequestStatus.PENDING);
        friendRequestRepository.save(request);

        // Notify receiver
        notificationService.createFriendRequestNotification(sender, receiver);

        return request;
    }


    public List<FriendRequest> getPendingRequestsForUser(String username) {
        User receiver = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return friendRequestRepository.findByReceiverAndStatus(receiver, FriendRequest.FriendRequestStatus.PENDING);
    }

    public void respondToFriendRequest(Long requestId, FriendRequest.FriendRequestStatus response) {
        FriendRequest request = friendRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Friend request not found"));
        if (response == FriendRequest.FriendRequestStatus.ACCEPTED) {
            // Add to each other's friend list
            request.getSender().getFriends().add(request.getReceiver());
            request.getReceiver().getFriends().add(request.getSender());
        }
        request.setStatus(response);
        friendRequestRepository.save(request);
    }

    public void removeFriend(String username, String friendUsername) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        User friend = userRepository.findByEmail(friendUsername)
                .orElseThrow(() -> new UsernameNotFoundException("Friend not found"));

        if (!user.getFriends().contains(friend)) {
            throw new IllegalArgumentException("User is not friends with " + friendUsername);
        }

        user.getFriends().remove(friend);
        friend.getFriends().remove(user);

        userRepository.save(user);
        userRepository.save(friend);
    }

}

