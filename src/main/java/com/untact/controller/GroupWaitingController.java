package com.untact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.untact.service.groupwaiting.GroupWaitingService;
import com.untact.vo.GroupWaitingVO;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/waiting/**")
@Log
public class GroupWaitingController {
	@Autowired
	private GroupWaitingService groupWaitingService;
	
	@PostMapping("/")
	public ResponseEntity<String> requestJoin(@RequestBody GroupWaitingVO groupWaitingVO	){
		groupWaitingService.requestJoin(groupWaitingVO);
		return new ResponseEntity<>("success",HttpStatus.OK);
	}
	
	@PutMapping("/{gwno}/accept")
	public ResponseEntity<String> acceptJoin(@PathVariable("gwno")Long gwno){
		groupWaitingService.acceptJoin(gwno);
		return new ResponseEntity<>("success",HttpStatus.OK);
	}
	
	@PutMapping("/{gwno}/reject")
	public ResponseEntity<String> rejectJoin(@PathVariable("gwno")Long gwno){
		groupWaitingService.rejectJoin(gwno);
		return new ResponseEntity<>("success",HttpStatus.OK);
	}
}
