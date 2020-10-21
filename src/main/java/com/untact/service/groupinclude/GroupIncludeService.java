package com.untact.service.groupinclude;

import com.untact.domain.member.MemberEntity;

public interface GroupIncludeService {
	public void groupWithdraw(Long gno,MemberEntity memberWantToWithdraw);
	public void groupEject(Long gno,MemberEntity leaderMember,MemberEntity memberWantToWithdraw);
}
