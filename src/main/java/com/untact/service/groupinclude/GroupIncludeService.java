package com.untact.service.groupinclude;

import com.untact.domain.member.MemberEntity;
import com.untact.vo.GroupWaitingVO;

public interface GroupIncludeService {
	public void groupWithdraw(Long gno,MemberEntity memberWantToWithdraw);
	public void groupEject(Long gno,MemberEntity leaderMember,MemberEntity memberWantToWithdraw);
	public void requestJoin(GroupWaitingVO groupWaitingVO);
	public void acceptJoin(Long gino);
	public void rejectJoin(Long gino);
}
