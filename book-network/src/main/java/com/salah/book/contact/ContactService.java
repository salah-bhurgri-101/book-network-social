package com.salah.book.contact;

import com.salah.book.common.PageResponse;
import com.salah.book.user.CustomUserDetail;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactMappper contactMappper;
    private final ContactRepository repository;

    public Integer saveContact(ContactRequest request, Authentication connectedUser) {
        CustomUserDetail user = (CustomUserDetail) connectedUser.getPrincipal();
        Contact contact = contactMappper.toContact(request);
        contact.setUser(user.getUser());
        return repository.save(contact).getId();
    }

    public ContactResponse findContactById(Integer contactId) {
        return repository.findById(contactId)
                .map(contactMappper::toContactReponse)
                .orElseThrow(() -> new EntityNotFoundException("NO Contact with Id :: " + contactId));
    }

    public PageResponse<ContactResponse> findAllByOwner(int page, int size, Authentication connectedUser) {
        CustomUserDetail user = (CustomUserDetail) connectedUser.getPrincipal();
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdData").descending());
        Page<Contact> contacts = repository.findAllByOwner(pageable , user.getUser().getId());
        List<ContactResponse> contactResponses = contacts.stream()
                .map(contactMappper::toContactReponse)
                .toList();
        return new PageResponse<>(
                contactResponses,
                contacts.getNumber(),
                contacts.getSize(),
                contacts.getTotalElements(),
                contacts.getTotalPages(),
                contacts.isFirst(),
                contacts.isLast()
        );
    }

    public Integer deleteContact(Integer contactId) {
        ContactResponse response = findContactById(contactId);
        Contact contact = contactMappper.toContactByReposne(response);
        repository.delete(contact);
        return contact.getId();
    }

    public void uploadImage(Integer contactId, MultipartFile file, Authentication connectedUser) throws IOException {
        Contact contact = repository.findById(contactId).orElseThrow(() -> new EntityNotFoundException("No Contact find with ID :: " + contactId));
//        CustomUserDetail customUserDetail = (CustomUserDetail)connectedUser.getPrincipal();
        byte[] bytes = file.getBytes();
        contact.setImage(bytes);
        repository.save(contact);
    }
}
