package com.untact.service.board;

import org.springframework.data.domain.Page;

import com.untact.domain.board.Board;
import com.untact.vo.PageVO;

public interface BoardService {
	public Page<Board> getListWithPagingAndGroupNumber(PageVO pageVO,Long gno);
	public void addBoard(Board board,Long gno);
	public void modifyBoard(Board targetBoard,Long bno);
	public void deleteBoard(Long bno);
}
