package com.untact.persistent.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.untact.domain.board.Board;

public interface BoardCustomRepository {
	public Page<Board> getPageWithGroupNumber(Pageable pageable, Long gno);
}
