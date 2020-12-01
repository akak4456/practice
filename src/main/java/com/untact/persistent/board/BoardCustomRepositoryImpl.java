package com.untact.persistent.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import com.untact.domain.board.Board;
import com.untact.domain.board.BoardKind;
import com.untact.domain.board.QBoard;
import com.untact.domain.member.MemberEntity;

public class BoardCustomRepositoryImpl extends QuerydslRepositorySupport implements BoardCustomRepository {

	public BoardCustomRepositoryImpl() {
		super(Board.class);
	}

	@Override
	public Page<Board> getPageWithGroupNumber(Pageable pageable, Long gno) {
		return getPageWithGroupNumberAndKind(pageable,gno,null);
	}

	@Override
	public Page<Board> getPageWithGroupNumberAndKind(Pageable pageable, Long gno, BoardKind kind) {
		QBoard board = QBoard.board;
		JPQLQuery<Tuple> query = from(board).select(board.bno,board.title,board.kind,board.member.name,board.member.role);
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(board.group.gno.eq(gno));
		if(kind != null) {
			builder.and(board.kind.eq(kind));
		}
		query.where(builder);
		Long totalCount = query.fetchCount();
		List<Tuple> list = getQuerydsl().applyPagination(pageable, query).fetch();
		List<Board> boardList = new ArrayList<>();
		for(Tuple t:list) {
			boardList.add(Board.builder()
					.bno(t.get(board.bno))
					.title(t.get(board.title))
					.kind(t.get(board.kind))
					.member(MemberEntity.builder().name(t.get(board.member.name)).role(t.get(board.member.role)).build())
					.build());
		}
		return new PageImpl<Board>(boardList,pageable,totalCount);
	}

}
