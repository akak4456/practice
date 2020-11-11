package com.untact.service.groupinclude;

import java.util.List;

import com.untact.domain.member.MemberEntity;
import com.untact.exception.NotGroupLeaderException;
import com.untact.vo.GroupWaitingVO;
import com.untact.vo.MemberManageVO;

public interface GroupIncludeService {
	public void groupWithdraw(Long gno,MemberEntity memberWantToWithdraw);
	public void groupEject(Long gno,MemberEntity leaderMember,MemberEntity memberWantToWithdraw);
	public void requestJoin(GroupWaitingVO groupWaitingVO);
	public void acceptJoin(Long gino);
	public void rejectJoin(Long gino);
	
	public boolean depositPay(Long gno,MemberEntity member);
	public List<MemberManageVO> getListWithGroupNumber(Long gno,MemberEntity member) throws NotGroupLeaderException;
}
