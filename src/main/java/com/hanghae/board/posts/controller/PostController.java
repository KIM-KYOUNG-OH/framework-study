package com.hanghae.board.posts.controller;

import com.hanghae.board.posts.dto.PostResponse;
import com.hanghae.board.posts.dto.PostRequest;
import com.hanghae.board.posts.entity.Post;
import com.hanghae.board.posts.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

        return posts.map(post -> PostResponse.of(post.getTitle(), post.getMember().getMemberName(), post.getContent(), post.getCreatedAt()));
    }

    @PostMapping
    public void savePosts(@RequestBody PostRequest request,
                          @AuthenticationPrincipal UserDetails userDetails) {

        postService.savePosts(request, userDetails);
    }

    @GetMapping("/{id}")
    public PostResponse findPosts(@PathVariable(value = "id") Long id) {

        Post findOne = postService.findBy(id);
        return PostResponse.of(findOne.getTitle(), findOne.getMember().getMemberName(), findOne.getContent(), findOne.getCreatedAt());
    }

    @PutMapping("/{id}")
    public void updatePosts(@PathVariable(value = "id") Long id,
                            @RequestBody PostRequest request,
                            @AuthenticationPrincipal UserDetails userDetails) {

        postService.update(id, request, userDetails);
    }

    @DeleteMapping("/{id}")
    public void deletePosts(@PathVariable(value = "id") Long id) {

        postService.delete(id);
    }
}
