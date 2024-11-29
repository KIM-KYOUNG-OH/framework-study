package com.hanghae.board.auth.controller;

import com.hanghae.board.auth.dto.LoginRequest;
import com.hanghae.board.auth.dto.SignUpRequest;
import com.hanghae.board.auth.exception.UsernameAlreadyExistsException;
import com.hanghae.board.global.service.JwtTokenService;
import com.hanghae.board.member.entity.Member;
import com.hanghae.board.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtTokenService.generateToken(userDetails.getUsername());
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SignUpRequest request) {

        memberRepository.findById(request.getUsername()).orElseThrow(
                () -> new UsernameAlreadyExistsException(String.format("Username '%s' already exists", request.getUsername())));

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        Member member = new Member();
        member.setMemberId(request.getUsername());
        member.setPassword(encodedPassword);
        member.setMemberName(request.getFullName());

        memberRepository.save(member);

        return "회원가입 성공!";
    }
}
