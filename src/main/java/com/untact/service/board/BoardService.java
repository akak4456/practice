package com.untact.service.board;

import java.util.List;

import org.springframework.data.domain.Page;

import com.untact.domain.board.Board;
import com.untact.domain.file.FileEntity;
import com.untact.domain.member.MemberEntity;
import com.untact.vo.PageVO;

public interface BoardService {
	public Page<Board> getListWithPagingAndGroupNumber(PageVO pageVO,Long gno);
	public Board getOne(Long bno);
	public void addBoard(Board board,Long gno,MemberEntity member);
	public void modifyBoard(Board targetBoard,Long bno);
	public void deleteBoard(Long bno);
}
