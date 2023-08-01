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
    // JPA Native query 사용
    // ANSI SQL이 사용가능할 때만 사용하는게 좋다
    // INSERT, UPDATE, DELETE는 JPA의 기본 메서드를 사용한다.
    // repository.save(entity), repository.deleteById(id);

    //@Query(value="SQL 문법", nativeQuery = true)
    @Query(value = "select * " +
                    "from contact " +
                    "order by name asc", nativeQuery = true)
    List<Contact> findContactsSortByName();

    //@Query(value="SQL 문법 :매개변수", nativeQuery = true)
    @Query(value = "select * " +
                    "from contact " +
                    "where email = :email", nativeQuery = true)
    Optional<Contact> findContactByEmail(String email);

    // Query Creation
    // https://docs.spring.io/spring-data/jpa/docs/1.6.0.RELEASE/reference/html/jpa.repositories.html

    // 실행되는 쿼리: "select * from contact order by name asc"
    List<Contact> findAllByOrderByName();
    // 실행되는 쿼리: "select * from contact where email = :email"
    Optional<Contact> findByEmail(String email);

}
