package com.untact.service.groupinclude;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.untact.domain.group.GroupEntity;
import com.untact.domain.groupinclude.GroupInclude;
import com.untact.domain.groupinclude.WhichStatus;
import com.untact.domain.member.MemberEntity;
import com.untact.exception.NotGroupLeaderException;
import com.untact.persistent.group.GroupEntityRepository;
import com.untact.persistent.groupinclude.GroupIncludeRepository;
import com.untact.persistent.member.MemberEntityRepository;
import com.untact.vo.GroupWaitingVO;
import com.untact.vo.MemberManageVO;

@Service
public class GroupIncludeServiceImpl implements GroupIncludeService {
	@Autowired
	private GroupEntityRepository groupRepo;
	@Autowired
	private MemberEntityRepository memberRepo;
	@Autowired
	private GroupIncludeRepository groupIncludeRepo;
	@Transactional
	@Override
	public void groupWithdraw(Long gno, MemberEntity memberWantToWithdraw) {
	}
	@Transactional
	@Override
	public void groupEject(Long gno, MemberEntity leaderMember, MemberEntity memberWantToWithdraw) {

	}
	@Override
	public boolean requestJoin(GroupWaitingVO groupWaitingVO) {
		Optional<GroupEntity> groupEntity = groupRepo.findById(groupWaitingVO.getGno());
		Optional<MemberEntity> memberEntity = memberRepo.findById(groupWaitingVO.getMno());
		if(groupIncludeRepo.findCountByGroupNumber(groupWaitingVO.getGno(), Set.of(WhichStatus.LEADER,WhichStatus.FOLLOWER)) >= groupEntity.get().getMaximumNumberOfPeople()) {
			//사람이 다꽉찾다면
			 return false;
		}
		GroupInclude include = GroupInclude.builder()
									.group(groupEntity.get())
									.member(memberEntity.get())
									.whichStatus(WhichStatus.WAITING)
									.deposit(0L)
									.fine(0L)
									.reward(0L)
									.attendance(0L)
									.absent(0L)
									.late(0L)
									.build();
		groupIncludeRepo.save(include);
		return true;
	}
	@Override
	public boolean acceptJoin(Long gno,Long gino,MemberEntity leader) {
		GroupEntity group = groupRepo.findById(gno).get();
		if(groupIncludeRepo.findCountByGroupNumber(gno, Set.of(WhichStatus.LEADER,WhichStatus.FOLLOWER)) >= group.getMaximumNumberOfPeople()) {
			//사람이 다꽉찾다면
			 return false;
		}
		if(groupIncludeRepo.findByGroupNumberAndMemberNumberAndWhichStatus(gno, leader.getMno(), WhichStatus.LEADER).isEmpty()) {
			return false;
		}
		groupIncludeRepo.updateStatusByGroupIncludeNumber(WhichStatus.FOLLOWER, gino);
		return true;
	}
	@Override
	public boolean rejectJoin(Long gno,Long gino,MemberEntity leader) {
		GroupEntity group = groupRepo.findById(gno).get();
		if(groupIncludeRepo.findCountByGroupNumber(gno, Set.of(WhichStatus.LEADER,WhichStatus.FOLLOWER)) >= group.getMaximumNumberOfPeople()) {
			//사람이 다꽉찾다면
			 return false;
		}
		if(groupIncludeRepo.findByGroupNumberAndMemberNumberAndWhichStatus(gno, leader.getMno(), WhichStatus.LEADER).isEmpty()) {
			return false;
		}
		groupIncludeRepo.updateStatusByGroupIncludeNumber(WhichStatus.REJECT, gino);
		return true;
	}
	@Override
	public boolean depositPay(Long gno, MemberEntity member) {
		GroupEntity group = groupRepo.findById(gno).get();
		
		if(member.getRemainPoint() >= group.getDepositToBePaid()) {
			GroupInclude toUpdate = groupIncludeRepo.findByGroupNumberAndMemberNumber(gno, member.getMno()).get();
			if(toUpdate.getDeposit() == 0L) {
				toUpdate.addDeposit(group.getDepositToBePaid());
				groupIncludeRepo.save(toUpdate);
				member.updateRemainPoint(member.getRemainPoint()-group.getDepositToBePaid());
				memberRepo.save(member);
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	

}
