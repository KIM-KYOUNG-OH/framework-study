package com.hanghae.board.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@ToString
public class Member {

    @Id
    @Column(name = "MEMBER_ID", nullable = false)
    private String memberId;

    @Column(name = "MEMBER_NAME", nullable = false, length = 50)
    private String memberName;

    @Column(name = "PASSWORD", nullable = false, length = 225)
    private String password;

    @Column(name = "CREATED_BY", nullable = false)
    private String createdBy;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_BY", nullable = false)
    private String updatedBy;

    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;

    public static Member of(String memberId) {
        return Member.builder()
                .memberId(memberId)
                .build();
    }
}
