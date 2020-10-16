package com.untact.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.untact.domain.member.MemberEntity;
import com.untact.security.AuthenticationFacade;
import com.untact.service.vocabulary.VocabularyService;
import com.untact.vo.NumberOfSuccessesOrFailures;

import lombok.extern.java.Log;

@RestController
@Log
public class VocabularyController {
	@Autowired
	private VocabularyService vocabularyService;
	@PostMapping("/vocabulary/{groupid}/{userid}")
	private ResponseEntity<NumberOfSuccessesOrFailures> addVocabulary(
			@RequestBody List<String> words,
			@PathVariable("groupid")Long gno,
			@PathVariable("userid")Long mno){
		MemberEntity member = AuthenticationFacade.getMemberEntityFromAuthentication();
		return new ResponseEntity<>(vocabularyService.addVocabulary(words, gno, member),HttpStatus.OK);
	}
	@PutMapping("vocabulary/{groupid}/{userid}")
	private ResponseEntity<String> deleteVocabulary(
			@RequestBody List<String> deleted,
			@PathVariable("groupid")Long gno,
			@PathVariable("userid")Long mno){
		MemberEntity member = AuthenticationFacade.getMemberEntityFromAuthentication();
		vocabularyService.deleteVocabulary(deleted, gno, member);
		return new ResponseEntity<>("success",HttpStatus.OK);
	}
}
