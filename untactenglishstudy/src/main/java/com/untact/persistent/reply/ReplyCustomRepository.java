package com.untact.persistent.reply;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.untact.domain.reply.Reply;

public interface ReplyCustomRepository {
	public Page<Reply> getPageWithBoardNumber(Pageable pageable,Long bno);
}
