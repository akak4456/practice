package com.untact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.untact.domain.member.MemberEntity;
import com.untact.security.JwtTokenProvider;
import com.untact.service.member.MemberService;
import com.untact.service.member.NoMatchMemberInformationException;
import com.untact.vo.MemberVO;

import lombok.extern.java.Log;

@RestController
@Log
public class MemberController {
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private MemberService memberService;
	@PostMapping("/member/sign_up")
	public ResponseEntity<String> addMember(@RequestBody MemberVO memberVO){
		memberService.addMember(memberVO);
		return new ResponseEntity<>("success",HttpStatus.OK);
	}
	@PostMapping("/member/login")
	public ResponseEntity<String> login(@RequestBody MemberVO memberVO){
		try {
			MemberEntity member = memberService.login(memberVO);
			return new ResponseEntity<>(jwtTokenProvider.createToken(member.getEmail(), member.getRole()),HttpStatus.OK);
		}catch(NoMatchMemberInformationException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
