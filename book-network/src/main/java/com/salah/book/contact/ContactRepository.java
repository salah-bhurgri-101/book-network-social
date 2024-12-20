package com.salah.book.contact;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContactRepository extends JpaRepository<Contact , Integer> {
    @Query("""
            SELECT contact
            FROM Contact contact
            WHERE contact.user.id = :userId
            """)
    Page<Contact> findAllByOwner(Pageable pageable, Integer userId);
}
