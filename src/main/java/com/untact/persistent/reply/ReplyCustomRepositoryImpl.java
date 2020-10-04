package com.untact.persistent.reply;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.untact.domain.reply.Reply;

public class ReplyCustomRepositoryImpl extends QuerydslRepositorySupport implements ReplyCustomRepository {
	public ReplyCustomRepositoryImpl() {
		super(Reply.class);
	}
}
