package com.untact.service.reply;

import org.springframework.data.domain.Page;

import com.untact.domain.reply.Reply;
import com.untact.vo.PageVO;

public interface ReplyService {
	public Page<Reply> getListWithPagingAndBoardNumber(PageVO pageVO,Long bno);
	public void addReply(Reply reply,Long gno,Long bno);
	public void modifyReply(Reply targetReply,Long rno);
	public void deleteReply(Long rno);
}
