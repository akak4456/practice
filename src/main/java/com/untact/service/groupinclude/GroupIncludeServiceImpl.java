package com.untact.service.groupinclude;

import java.util.List;
import java.util.Optional;

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
	public void requestJoin(GroupWaitingVO groupWaitingVO) {
		Optional<GroupEntity> groupEntity = groupRepo.findById(groupWaitingVO.getGno());
		Optional<MemberEntity> memberEntity = memberRepo.findById(groupWaitingVO.getMno());
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
	}
	@Override
	public void acceptJoin(Long gino) {
		groupIncludeRepo.updateStatusByGroupIncludeNumber(WhichStatus.FOLLOWER, gino);
	}
	@Override
	public void rejectJoin(Long gino) {
		groupIncludeRepo.updateStatusByGroupIncludeNumber(WhichStatus.REJECT, gino);
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
	@Override
	public List<MemberManageVO> getListWithGroupNumber(Long gno,MemberEntity member) throws NotGroupLeaderException {
		Optional<GroupInclude> include = groupIncludeRepo.findByGroupNumberAndMemberNumberAndWhichStatus(gno, member.getMno(), WhichStatus.LEADER);
		if(include.isEmpty()) {
			throw new NotGroupLeaderException();
		}
		return groupIncludeRepo.findMemberManageByGroupNumber(gno);
	}

}
