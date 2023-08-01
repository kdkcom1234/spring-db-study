package com.kdk.myapp.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
// JpaRepository<Contact, String>
// <엔티티클래스, 엔티티의키타입>
public interface ContactRepository extends JpaRepository<Contact, String> {
    @Query(value = "select * " +
            "from contact " +
            "order by name asc ", nativeQuery = true)
    List<Contact> findContactsSortByName();

    @Query(value = "select * " +
            "from contact " +
            "where email = :email ", nativeQuery = true)
    Optional<Contact> findContactByEmail(String email);
}
