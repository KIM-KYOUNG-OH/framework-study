package com.hanghae.board.service;

import com.hanghae.board.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface PostService {

    Page<Post> getAllPosts(PageRequest pageRequest);
}
