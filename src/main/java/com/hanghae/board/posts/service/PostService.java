package com.hanghae.board.posts.service;

import com.hanghae.board.posts.entity.Post;
import com.hanghae.board.posts.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public Page<Post> getAllPosts(PageRequest pageRequest) {

        return postRepository.findAll(pageRequest);
    }
}
