package com.untact.service.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.untact.domain.reply.Reply;
import com.untact.persistent.board.BoardRepository;
import com.untact.persistent.group.GroupEntityRepository;
import com.untact.persistent.reply.ReplyRepository;
import com.untact.vo.PageVO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	private ReplyRepository replyRepo;
	
	@Autowired
	private GroupEntityRepository groupEntityRepo;
	
	@Autowired
	private BoardRepository boardRepo;

	@Override
	public Page<Reply> getListWithPagingAndBoardNumber(PageVO pageVO, Long bno) {
		return replyRepo.getPageWithBoardNumber(pageVO.makePageable(0, "rno"), bno);
	}

	@Override
	public void addReply(Reply reply,Long gno,Long bno) {
		reply.setGroup(groupEntityRepo.findById(gno).get());
		reply.setBoard(boardRepo.findById(bno).get());
		replyRepo.save(reply);
	}

	@Override
	public void modifyReply(Reply targetReply, Long rno) {
		Reply oldReply = replyRepo.findById(rno).get();
		Reply updatedReply = oldReply.modifyThisToTargetReply(targetReply);
		replyRepo.save(updatedReply);
	}

	@Override
	public void deleteReply(Long rno) {
		replyRepo.deleteById(rno);
	}
}
