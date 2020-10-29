package com.untact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.untact.domain.member.MemberEntity;
import com.untact.security.AuthenticationFacade;
import com.untact.service.member.MemberService;
import com.untact.vo.MemberVO;

import lombok.extern.java.Log;

@RestController
@Log
public class InfoController {
	@Autowired
	private MemberService memberService;
	@PutMapping("/member/info")
	public ResponseEntity<String> changeInfo(@RequestBody MemberVO memberVO){
		MemberEntity member = AuthenticationFacade.getMemberEntityFromAuthentication();
		if(!member.getEmail().equals(memberVO.getEmail())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		memberService.changeInfo(member, memberVO);
		return new ResponseEntity<>("success",HttpStatus.OK);
	}
	@DeleteMapping("/member/info")
	public ResponseEntity<String> deleteInfo(){
		MemberEntity member = AuthenticationFacade.getMemberEntityFromAuthentication();
		memberService.deleteInfo(member.getMno());
		return new ResponseEntity<>("success",HttpStatus.OK);
	}
}
