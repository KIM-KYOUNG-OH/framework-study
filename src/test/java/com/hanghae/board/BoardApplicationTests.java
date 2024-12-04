package com.hanghae.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class BoardApplicationTests {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	void contextLoads() {
	}

	@Test
	void test() {
		String encrpytedPW = passwordEncoder.encode("password123");  // $2a$10$OOg6rU0L1YeMNNDboRUUZurHRvNtL8sCdQOUDehp.G76HXfQmCEA6
		System.out.println("encrpytedPW = " + encrpytedPW);
	}
}
