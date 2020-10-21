package com.untact.service.groupinclude;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.untact.domain.group.GroupEntity;
import com.untact.domain.member.MemberEntity;
import com.untact.persistent.group.GroupEntityRepository;

@Service
public class GroupIncludeServiceImpl implements GroupIncludeService {
	@Autowired
	private GroupEntityRepository groupRepo;
	@Transactional
	@Override
	public void groupWithdraw(Long gno, MemberEntity memberWantToWithdraw) {
		GroupEntity group = groupRepo.findById(gno).get();
	}
	@Transactional
	@Override
	public void groupEject(Long gno, MemberEntity leaderMember, MemberEntity memberWantToWithdraw) {
		// TODO Auto-generated method stub

	}

}
