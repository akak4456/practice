package com.untact.service.invite;

import com.untact.domain.member.MemberEntity;

public interface InviteService {
	public boolean inviteAccept(Long gno,MemberEntity member,String inviteCode);
}
