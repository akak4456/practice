package com.untact.persistent.reply;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.JPQLQuery;
import com.untact.domain.reply.QReply;
import com.untact.domain.reply.Reply;

public class ReplyCustomRepositoryImpl extends QuerydslRepositorySupport implements ReplyCustomRepository {
	public ReplyCustomRepositoryImpl() {
		super(Reply.class);
	}

	@Override
	public Page<Reply> getPageWithBoardNumber(Pageable pageable, Long bno) {
		QReply reply = QReply.reply;
		JPQLQuery<Reply> query = from(reply);
		query.where(reply.board.bno.eq(bno));
		return makePage(pageable,query);
	}
	
	private Page<Reply> makePage(Pageable pageable, JPQLQuery<Reply> query){
		Long totalCount = query.fetchCount();
		List<Reply> list = getQuerydsl().applyPagination(pageable, query).fetch();
		return new PageImpl<Reply>(list,pageable,totalCount);
	}
}
