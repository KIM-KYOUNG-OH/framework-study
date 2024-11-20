package com.hanghae.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID", nullable = false)
    private Integer memberId;

    @Column(name = "MEMBER_NAME", nullable = false, length = 50)
    private String memberName;

    @Column(name = "PASSWORD", nullable = false, length = 50)
    private String password;

    @Column(name = "ACCESS_TOKEN", length = 500)
    private String accessToken;

    @Column(name = "REFRESH_TOKEN", columnDefinition = "TEXT")
    private String refreshToken;

    @Column(name = "CREATED_BY", nullable = false)
    private Integer createdBy;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_BY", nullable = false)
    private Integer updatedBy;

    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();
}
