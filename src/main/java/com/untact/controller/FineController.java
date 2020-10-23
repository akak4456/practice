package com.untact.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.untact.domain.fine.Fine;
import com.untact.domain.member.MemberEntity;
import com.untact.security.AuthenticationFacade;
import com.untact.service.fine.FineService;
import com.untact.vo.AmountVO;

import lombok.extern.java.Log;

@RestController
@Log
public class FineController {
	@Autowired
	private FineService fineService;
	
	@GetMapping("/fine/{groupid}")
	public ResponseEntity<Fine> getFine(@PathVariable("groupid")Long gno) {
		MemberEntity member = AuthenticationFacade.getMemberEntityFromAuthentication();
		try {
			return new ResponseEntity<>(fineService.getFineWithGroupNumberAndMemberNumber(gno, member.getMno()),HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping("/fine/{groupid}")
	public ResponseEntity<String> addDeposit(@PathVariable("groupid")Long gno,@RequestBody AmountVO amountVO){
		MemberEntity member = AuthenticationFacade.getMemberEntityFromAuthentication();
		try {
			fineService.addFine(gno, member, amountVO);
			return new ResponseEntity<>("success",HttpStatus.OK);
		}catch(DataIntegrityViolationException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping("/fine/{groupid}/{depositid}")
	public ResponseEntity<String> changeDeposit(@PathVariable("groupid")Long gno,@PathVariable("depositid")Long dno,@RequestBody AmountVO amountVO){
		MemberEntity member = AuthenticationFacade.getMemberEntityFromAuthentication();
		if(fineService.changeFine(dno, gno, member, amountVO)) {
			return new ResponseEntity<>("success",HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
