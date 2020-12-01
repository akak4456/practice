package com.untact.persistent.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.untact.domain.board.Board;
import com.untact.domain.board.BoardKind;

public interface BoardCustomRepository {
	public Page<Board> getPageWithGroupNumber(Pageable pageable, Long gno);
	public Page<Board> getPageWithGroupNumberAndKind(Pageable pageable,Long gno,BoardKind kind);
}
