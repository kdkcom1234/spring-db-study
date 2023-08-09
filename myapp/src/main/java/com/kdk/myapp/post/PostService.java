package com.kdk.myapp.post;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class PostService {

    @Autowired
    PostRepository repo;

    @Autowired
    PostCommentRepository commentRepo;

    @Transactional
    public void createComment(Post post, PostComment comment) {
        commentRepo.save(comment);
        repo.save(post);
    }


}
