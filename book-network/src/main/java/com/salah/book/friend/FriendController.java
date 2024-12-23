package com.salah.book.friend;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("friends")
@RequiredArgsConstructor
@Tag(name = "Friend")
public class FriendController {

    private final FriendRequestService friendRequestService;

    @PostMapping("/request")
    public ResponseEntity<String> sendFriendRequest(@RequestParam String sender, @RequestParam String receiver) {
        friendRequestService.sendFriendRequest(sender, receiver);
        return ResponseEntity.ok("Friend request sent!");
    }

    @GetMapping("/requests")
    public ResponseEntity<List<FriendRequest>> getPendingRequests(@RequestParam String username) {
        List<FriendRequest> requests = friendRequestService.getPendingRequestsForUser(username);
        return ResponseEntity.ok(requests);
    }

    @PostMapping("/requests/{id}/respond")
    public ResponseEntity<String> respondToRequest(@PathVariable Long id, @RequestParam FriendRequest.FriendRequestStatus status) {
        friendRequestService.respondToFriendRequest(id, status);
        return ResponseEntity.ok("Friend request " + status);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFriend(@RequestParam String username, @RequestParam String friendUsername) {
        friendRequestService.removeFriend(username, friendUsername);
        return ResponseEntity.ok("Friend removed successfully");
    }
}

