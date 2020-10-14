package com.untact.persistent.board;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.JPQLQuery;
import com.untact.domain.board.Board;
import com.untact.domain.board.QBoard;
import com.untact.domain.group.GroupEntity;
import com.untact.domain.group.QGroupEntity;
import com.untact.domain.groupinclude.QGroupInclude;

public class BoardCustomRepositoryImpl extends QuerydslRepositorySupport implements BoardCustomRepository {

	public BoardCustomRepositoryImpl() {
		super(Board.class);
	}

	@Override
	public Page<Board> getPageWithGroupNumber(Pageable pageable, Long gno) {
		QBoard board = QBoard.board;
		JPQLQuery<Board> query = from(board);
		query.where(board.group.gno.eq(gno));
		return makePage(pageable,query);
	}
	
	private Page<Board> makePage(Pageable pageable, JPQLQuery<Board> query){
		Long totalCount = query.fetchCount();
		List<Board> list = getQuerydsl().applyPagination(pageable, query).fetch();
		return new PageImpl<Board>(list,pageable,totalCount);
	}

}
