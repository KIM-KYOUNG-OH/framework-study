package com.hanghae.board.posts.dto;

import com.hanghae.board.posts.entity.Post;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class PostResponse {

    private String title;
    private String memberName;
    private String content;
    private LocalDateTime createdAt;

    public static PostResponse of(String title, String memberName, String content, LocalDateTime createdAt) {
        return PostResponse.builder()
                .title(title)
                .memberName(memberName)
                .content(content)
                .createdAt(createdAt)
                .build();
    }
}
