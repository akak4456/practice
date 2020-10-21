package com.untact.persistent.groupinclude;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.untact.domain.group.GroupEntity;
import com.untact.domain.groupinclude.GroupInclude;
import com.untact.domain.member.MemberEntity;

public class GroupIncludeCustomRepositoryImpl extends QuerydslRepositorySupport
		implements GroupIncludeCustomRepository {
	public GroupIncludeCustomRepositoryImpl() {
		super(GroupInclude.class);
	}

	@Override
	public void deleteGroupIncludeAndChangeGroupWaitingByGroupAndMember(GroupEntity group, MemberEntity member) {
		
	}
}
