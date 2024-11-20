package com.hanghae.board.service;

import com.hanghae.board.entity.Post;
import com.hanghae.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public Page<Post> getAllPosts(PageRequest pageRequest) {

        return postRepository.findAll(pageRequest);
    }
}
