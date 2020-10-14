package com.untact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.untact.domain.board.Board;
import com.untact.service.board.BoardService;
import com.untact.vo.PageMaker;
import com.untact.vo.PageVO;

import lombok.extern.java.Log;

@RestController
@Log
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board/{groupid}")
	public ResponseEntity<PageMaker<Board>> getBoards(PageVO pageVO,@PathVariable("groupid")Long gno) {
		log.info(pageVO.getPage()+"");
		return new ResponseEntity<>(new PageMaker<Board>(boardService.getListWithPagingAndGroupNumber(pageVO, gno),pageVO),HttpStatus.OK);
	}
	
	@PostMapping("/board/{groupid}")
	public ResponseEntity<String> addBoard(@RequestBody Board board,@PathVariable("groupid")Long gno){
		boardService.addBoard(board,gno);
		return new ResponseEntity<>("success",HttpStatus.OK);
	}
	
	@PutMapping("/board/{boardid}")
	public ResponseEntity<String> modifyBoard(@RequestBody Board targetBoard,@PathVariable("boardid")Long bno){
		boardService.modifyBoard(targetBoard, bno);
		return new ResponseEntity<>("success",HttpStatus.OK);
	}
	@DeleteMapping("/board/{boardid}")
	public ResponseEntity<String> deleteBoard(@PathVariable("boardid")Long bno){
		boardService.deleteBoard(bno);
		return new ResponseEntity<>("success",HttpStatus.OK);
	}
}
