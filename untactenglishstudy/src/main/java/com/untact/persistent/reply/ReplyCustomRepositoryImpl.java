package com.untact.persistent.reply;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import com.untact.domain.board.Board;
import com.untact.domain.board.QBoard;
import com.untact.domain.member.MemberEntity;
import com.untact.domain.reply.QReply;
import com.untact.domain.reply.Reply;

public class ReplyCustomRepositoryImpl extends QuerydslRepositorySupport implements ReplyCustomRepository {
	public ReplyCustomRepositoryImpl() {
		super(Reply.class);
	}

	@Override
	public Page<Reply> getPageWithBoardNumber(Pageable pageable, Long bno) {
		QReply reply = QReply.reply;
		JPQLQuery<Tuple> query = from(reply).select(reply.rno,reply.message,reply.updatedate,reply.member.mno,reply.member.name,reply.member.role);
		query.where(reply.board.bno.eq(bno));
		Long totalCount = query.fetchCount();
		List<Tuple> list = getQuerydsl().applyPagination(pageable, query).fetch();
		List<Reply> replyList = new ArrayList<>();
		for(Tuple t:list) {
			replyList.add(Reply.builder()
							.rno(t.get(reply.rno))
							.message(t.get(reply.message))
							.updatedate(t.get(reply.updatedate))
							.member(MemberEntity.builder().mno(t.get(reply.member.mno)).name(t.get(reply.member.name)).role(t.get(reply.member.role)).build())
							.build()
					);
		}
		return new PageImpl<Reply>(replyList,pageable,totalCount);
	}
	
}
