package com.untact.persistent.groupinclude;

import java.util.List;

import com.untact.domain.member.MemberEntity;

public interface GroupIncludeCustomRepository {
	public List<MemberEntity> findMemberByGroupNumber(Long gno);
}
