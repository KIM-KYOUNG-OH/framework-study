package com.hanghae.board.controller;

import com.hanghae.board.dto.PostResponse;
import com.hanghae.board.entity.Post;
import com.hanghae.board.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public Page<PostResponse> getAllPosts(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                          @RequestParam(value = "size", defaultValue = "10") Integer size,
                                          @RequestParam(value = "sort", defaultValue = "createdAt") String sort,
                                          @RequestParam(value = "order", defaultValue = "asc") String order) {

        Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(direction, sort));

        Page<Post> posts = postService.getAllPosts(pageRequest);

        return posts.map(post -> new PostResponse(post.getTitle(), post.getMember().getMemberName(), post.getContent(), post.getCreatedAt()));
    }
}
