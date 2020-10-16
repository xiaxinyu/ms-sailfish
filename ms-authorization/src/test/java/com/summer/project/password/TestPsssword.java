package com.summer.project.password;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestPsssword {
	public static void main(String[] args) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode("summer");
		System.out.println(encodedPassword);
		
		encodedPassword = passwordEncoder.encode("123456");
		System.out.println(encodedPassword);


		boolean f = passwordEncoder.matches("123456","$2a$10$LfAHuQfPDUkdatk5kenlZuj2iGx5UEtnnKTpgG7lNBGYk1q./TEli");
		System.out.println(f);
	}
}
