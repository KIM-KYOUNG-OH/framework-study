package com.hanghae.board.global.service;

import com.hanghae.board.member.entity.Member;
import com.hanghae.board.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findById(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found: " + username));

        if (member.getMemberId().equals(username)) {
            return new User(member.getMemberId(), member.getPassword(), Collections.emptyList());
        }
        throw new UsernameNotFoundException("User not found: " + username);
    }
}
