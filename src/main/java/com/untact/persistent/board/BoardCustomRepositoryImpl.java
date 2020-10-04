package com.untact.persistent.board;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.untact.domain.board.Board;

public class BoardCustomRepositoryImpl extends QuerydslRepositorySupport implements BoardCustomRepository {

	public BoardCustomRepositoryImpl() {
		super(Board.class);
		// TODO Auto-generated constructor stub
	}

}
