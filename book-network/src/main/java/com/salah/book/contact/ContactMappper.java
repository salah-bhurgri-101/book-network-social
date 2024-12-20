package com.salah.book.contact;

import org.springframework.stereotype.Service;

@Service
public class ContactMappper {

    public Contact toContact(ContactRequest request) {
        return Contact.builder()
                .id(request.id())
                .name(request.name())
                .secondName(request.secondName())
                .work(request.work())
                .description(request.description())
                .email(request.email())
                .phone(request.phone())
                .build();
    }

    public ContactResponse toContactReponse(Contact contact) {
        return ContactResponse.builder()
                .id(contact.getId())
                .name(contact.getName())
                .secondName(contact.getSecondName())
                .phone(contact.getPhone())
                .email(contact.getEmail())
                .work(contact.getWork())
                .description(contact.getDescription())
                .image(contact.getImage())
                .build();
    }

    public Contact toContactByReposne(ContactResponse response) {
        return Contact.builder()
                .id(response.getId())
                .name(response.getName())
                .secondName(response.getSecondName())
                .work(response.getWork())
                .email(response.getEmail())
                .phone(response.getPhone())
                .description(response.getDescription())
                .image(response.getImage())
                .build();
    }
}
