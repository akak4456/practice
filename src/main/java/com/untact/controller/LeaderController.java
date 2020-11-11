package com.untact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.untact.domain.member.MemberEntity;
import com.untact.exception.NotGroupLeaderException;
import com.untact.security.AuthenticationFacade;
import com.untact.service.groupinclude.GroupIncludeService;
import com.untact.vo.MemberManageResponse;

import lombok.extern.java.Log;

@RestController
@Log
public class LeaderController {
	@Autowired
	private GroupIncludeService groupIncludeService;
	
	@GetMapping("/leader/{groupid}")
	public ResponseEntity<MemberManageResponse> getList(@PathVariable("groupid")Long gno){
		MemberEntity member = AuthenticationFacade.getMemberEntityFromAuthentication();
		try {
			MemberManageResponse response = new MemberManageResponse();
			response.setMembers(groupIncludeService.getListWithGroupNumber(gno, member));
			return new ResponseEntity<>(response,HttpStatus.OK);
		}catch(NotGroupLeaderException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
