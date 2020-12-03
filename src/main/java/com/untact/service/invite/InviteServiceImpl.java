package com.untact.service.invite;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.untact.domain.group.GroupEntity;
import com.untact.domain.groupinclude.GroupInclude;
import com.untact.domain.groupinclude.WhichStatus;
import com.untact.domain.member.MemberEntity;
import com.untact.persistent.group.GroupEntityRepository;
import com.untact.persistent.groupinclude.GroupIncludeRepository;

@Service
public class InviteServiceImpl implements InviteService {
	@Autowired
	private GroupEntityRepository groupRepo;
	@Autowired
	private GroupIncludeRepository groupIncludeRepo;
	
	@Override
	public boolean inviteAccept(Long gno, MemberEntity member, String inviteCode) {
		if(groupIncludeRepo.findByGroupNumberAndMemberNumber(gno, member.getMno()).isPresent()) {
			return false;
		}
		Optional<GroupEntity> group = groupRepo.findById(gno);
		if(group.isEmpty()) {
			return false;
		}
		if(!group.get().getInviteCode().equals(inviteCode)) {
			return false;
		}
		groupIncludeRepo.save(groupIncludeRepo.save(GroupInclude.builder()
				.group(group.get())
				.member(member)
				.whichStatus(WhichStatus.FOLLOWER)
				.deposit(0L)
				.fine(0L)
				.reward(0L)
				.attendance(0L)
				.absent(0L)
				.late(0L)
				.build()));//그룹에 가입시켜준다
		return true;
	}

}
