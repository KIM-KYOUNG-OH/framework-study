package com.hanghae.board.posts.repository;

import com.hanghae.board.posts.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
