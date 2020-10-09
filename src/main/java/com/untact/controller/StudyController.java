package com.untact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.untact.domain.group.GroupEntity;
import com.untact.service.group.GroupService;
import com.untact.vo.PageMaker;
import com.untact.vo.PageVO;

import lombok.extern.java.Log;

@RestController
@Log
public class StudyController {
	@Autowired
	private GroupService groupService;
	@GetMapping("/study")
	public ResponseEntity<PageMaker<GroupEntity>> getAllStudy(PageVO pageVO){
		return new ResponseEntity<>(new PageMaker<GroupEntity>(groupService.getListWithPaging(pageVO),pageVO),HttpStatus.OK);
	}
	@GetMapping("/study/{userid}")
	public ResponseEntity<PageMaker<GroupEntity>> getMyStudy(PageVO pageVO,@PathVariable("userid") Long mno){
		return new ResponseEntity<>(new PageMaker<GroupEntity>(groupService.getListWithPagingAndUserNumber(pageVO, mno),pageVO),HttpStatus.OK);
	}
	@PostMapping("/study")
	public ResponseEntity<String> addStudy(@RequestBody GroupEntity group){
		log.info(group.getTitle());
		groupService.addGroup(group);
		return new ResponseEntity<>("success",HttpStatus.OK);
	}
}
