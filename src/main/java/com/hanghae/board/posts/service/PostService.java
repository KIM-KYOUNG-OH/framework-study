package com.hanghae.board.posts.service;

import com.hanghae.board.global.exception.ResourceNotFoundException;
import com.hanghae.board.member.entity.Member;
import com.hanghae.board.posts.dto.PostRequest;
import com.hanghae.board.posts.entity.Post;
import com.hanghae.board.posts.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public Page<Post> getAllPosts(PageRequest pageRequest) {

        return postRepository.findAll(pageRequest);
    }

    @Transactional
    public void savePosts(PostRequest request, UserDetails userDetails) {

        Post post = Post.of(userDetails.getUsername(), request.getTitle(), request.getContent());
        postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public Post findBy(Long postId) {

        return postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with id : " + postId));
    }

    @Transactional
    public void update(Long postId, PostRequest request, UserDetails userDetails) {

        Post findOne = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with id : " + postId));

        boolean isUpdate = false;
        if (request.getTitle() != null) {
            findOne.setTitle(request.getTitle());
            isUpdate = true;
        }

        if (request.getContent() != null) {
            findOne.setContent(request.getContent());
            isUpdate = true;
        }

        if (isUpdate) {
            findOne.setMember(Member.of(userDetails.getUsername()));
            findOne.setUpdatedBy(userDetails.getUsername());
            findOne.setUpdatedAt(LocalDateTime.now());
        }
    }

    @Transactional
    public void delete(Long postId) {

        Post findOne = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with id : " + postId));

        postRepository.delete(findOne);
    }
}
