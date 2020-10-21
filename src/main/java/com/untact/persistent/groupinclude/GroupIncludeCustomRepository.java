package com.untact.persistent.groupinclude;

import com.untact.domain.group.GroupEntity;
import com.untact.domain.member.MemberEntity;

public interface GroupIncludeCustomRepository {
	public void deleteGroupIncludeAndChangeGroupWaitingByGroupAndMember(GroupEntity group,MemberEntity member);
}
