package com.kdk.myapp.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// JpaRepository<Contact, String>
// <엔티티클래스, 엔티티의키타입>
public interface ContactRepository extends JpaRepository<Contact, String> {
}
