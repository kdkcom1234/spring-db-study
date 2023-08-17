package com.kdk.myapp.post;

import com.kdk.myapp.post.entity.Post;
import com.kdk.myapp.post.entity.PostComment;
import com.kdk.myapp.post.repository.PostCommentRepository;
import com.kdk.myapp.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
