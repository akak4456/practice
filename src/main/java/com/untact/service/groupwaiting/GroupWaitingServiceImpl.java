package com.untact.service.groupwaiting;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.untact.domain.group.GroupEntity;
import com.untact.domain.groupwaiting.GroupWaiting;
import com.untact.domain.groupwaiting.GroupWaitingStatus;
import com.untact.domain.member.MemberEntity;
import com.untact.persistent.group.GroupEntityRepository;
import com.untact.persistent.groupwaiting.GroupWaitingRepository;
import com.untact.persistent.member.MemberEntityRepository;
import com.untact.vo.GroupWaitingVO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Service
@NoArgsConstructor
@AllArgsConstructor
public class GroupWaitingServiceImpl implements GroupWaitingService {
	@Autowired
	private MemberEntityRepository memberRepo;
	@Autowired
	private GroupEntityRepository groupRepo;
	@Autowired
	private GroupWaitingRepository groupWaitingRepo;
	@Override
	public void requestJoin(GroupWaitingVO groupWaitingVO) {
		Optional<GroupEntity> groupEntity = groupRepo.findById(groupWaitingVO.getGno());
		Optional<MemberEntity> memberEntity = memberRepo.findById(groupWaitingVO.getMno());
		GroupWaiting waitingEntity = GroupWaiting.builder().group(groupEntity.get()).member(memberEntity.get()).status(GroupWaitingStatus.WAIT).build();
		groupWaitingRepo.save(waitingEntity);
	}

	@Override
	public void acceptJoin(Long gwno) {
		groupWaitingRepo.changeStatus(GroupWaitingStatus.ACCEPT, gwno);
	}

	@Override
	public void rejectJoin(Long gwno) {
		groupWaitingRepo.changeStatus(GroupWaitingStatus.REJECT, gwno);
	}

}
