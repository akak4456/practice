package com.untact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.untact.domain.member.MemberEntity;
import com.untact.security.AuthenticationFacade;
import com.untact.service.attendance.AttendanceService;

@RestController
public class AttendanceController {
	@Autowired
	private AttendanceService attendanceService;
	@PutMapping("/attendance/{groupid}")
	public ResponseEntity<String> attendanceCheck(@PathVariable("groupid")Long gno){
		MemberEntity member = AuthenticationFacade.getMemberEntityFromAuthentication();
		return new ResponseEntity<>(attendanceService.attendanceCheck(gno, member.getMno()).toString(),HttpStatus.OK);
	}
}
