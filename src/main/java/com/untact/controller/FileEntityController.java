package com.untact.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.untact.service.filesystemmanipulate.FileSystemManipulateService;
import com.untact.vo.UrlVO;

@RestController
public class FileEntityController {
	@Autowired
	private FileSystemManipulateService fileSystemManipulateService;
	@PostMapping("/upload")
	public ResponseEntity<UrlVO> fileUpload(@RequestParam("upload")MultipartFile uploadFile){
		try {
			return new ResponseEntity<>(new UrlVO(fileSystemManipulateService.saveFile(uploadFile)),HttpStatus.OK);
		}catch(IOException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}catch(IllegalStateException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/download/{year}/{month}/{date}/{name}")
	public ResponseEntity<byte[]> getOne(
			@PathVariable("year") String year,
			@PathVariable("month") String month,
			@PathVariable("date") String date,
			@PathVariable("name") String name){
		try {
			byte[] bytes = fileSystemManipulateService.getFile(year,month,date,name);
			return new ResponseEntity<byte[]>(bytes,HttpStatus.OK);
		}catch(IOException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
