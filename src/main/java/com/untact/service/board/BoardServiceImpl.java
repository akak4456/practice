package com.untact.service.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.untact.domain.board.Board;
import com.untact.domain.member.MemberEntity;
import com.untact.persistent.board.BoardRepository;
import com.untact.persistent.group.GroupEntityRepository;
import com.untact.vo.PageVO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{
	/*
	 주의 아직까지는 MemberEnttiy와의 의존관계는 맺지 않음,
	 그러나 이것은 나중에 필요함
	 */
	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private GroupEntityRepository groupEntityRepo;

	@Override
	public Page<Board> getListWithPagingAndGroupNumber(PageVO pageVO, Long gno) {
		return boardRepo.getPageWithGroupNumber(pageVO.makePageable(0, "bno"), gno);
	}

	@Override
	public void addBoard(Board board,Long gno,MemberEntity member) {
		board.setGroup(groupEntityRepo.findById(gno).get());
		board.setMember(member);
		boardRepo.save(board);
	}

	@Override
	public void modifyBoard(Board targetBoard,Long bno) {
		Board oldBoard = boardRepo.findById(bno).get();
		Board updatedBoard = oldBoard.modifyThisToTargetBoard(targetBoard);
		boardRepo.save(updatedBoard);
	}

	@Override
	public void deleteBoard(Long bno) {
		//연관관계에 있는 나머지 것들도 삭제 또는 변경 해야 함
		boardRepo.deleteById(bno);
	}
}
