package com.kdk.myapp.contact;

//import org.springframework.data.domain.Page;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
// JpaRepository<Contact, ContactId>
// <엔티티클래스, 엔티티의키타입>
public interface ContactRepository extends JpaRepository<Contact, ContactId> {
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
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#appendix.query.method.subject

    // 실행되는 쿼리: "select * from contact order by name asc"
    List<Contact> findAllByOrderByName();
    // 실행되는 쿼리: "select * from contact where email = :email"
    Optional<Contact> findByEmail(String email);

    // Spring Data Query Creation
    // 메서드 시그니처(이름, 매개변수개수/타입)에 맞게 SQL문을 생성해줌
    /* SELECT * FROM contact WHERE name = :name
        ORDER BY :sort
        LIMIT :size
        OFFSET :size * :page

        -- 반환 타입이 Page가 아니면 count()는 안 함
        SELECT count(*) FROM contact WHERE name = :name
     */
    Page<Contact> findByName(String name, Pageable page);
    /* SELECT * FROM contact WHERE name LIKE '%:name%'
    ORDER BY :sort
    LIMIT :size
    OFFSET :size * :page

    -- 반환 타입이 Page가 아니면 count()는 안 함
    SELECT count(*) FROM contact WHERE name LIKE '%:name%'
 */
    Page<Contact> findByNameContaining(String name, Pageable page);

    Page<Contact> findByNameContainsOrPhoneContains
            (String name, String phone, Pageable pageable);

    // select * from contact where owner_id = :ownerId;
    // select count(*) from contact where owner_id = :ownerId;
    Page<Contact> findByOwnerId(long ownerId, Pageable pageable);


}
