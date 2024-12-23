package com.salah.book.contact;

import com.salah.book.common.PageResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("contacts")
@RequiredArgsConstructor
@Tag(name = "Contact")
public class ContactController {

    private final ContactService service;

    @PostMapping
    public ResponseEntity<Integer> saveContact(
            @Valid @RequestBody ContactRequest request,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.saveContact(request , connectedUser));
    }

    @GetMapping("{contact-id}")
    public ResponseEntity<ContactResponse> getContactById(
            @PathVariable("contact-id") Integer contactId
    ){
        return ResponseEntity.ok(service.findContactById(contactId));
    }

    @GetMapping("owner")
    public ResponseEntity<PageResponse<ContactResponse>> findAllByOwner(
            @RequestParam(name = "page" , defaultValue = "0" , required = false) int page,
            @RequestParam(name = "size" , defaultValue = "10", required = false) int size,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.findAllByOwner(page, size , connectedUser));
    }

    @DeleteMapping("{contact-id}")
    public ResponseEntity<Integer> deleteContact(
            @PathVariable("contact-id") Integer contactId
    ){
        return ResponseEntity.ok(service.deleteContact(contactId));
    }

    @PostMapping(value = "{contact-id}" , consumes = "multipart/form-data")
    public ResponseEntity<?> uploadImage(
            @PathVariable("contact-id") Integer contactId,
            @Parameter
            @RequestPart("file")MultipartFile file,
            Authentication connectedUser
            ) throws IOException {
        service.uploadImage(contactId , file , connectedUser);
        return ResponseEntity.accepted().build();
    }

}
