package com.untact.service.member;

import com.untact.domain.member.MemberEntity;
import com.untact.exception.NoMatchMemberInformationException;
import com.untact.vo.MemberVO;

public interface MemberService {
	public void addMember(MemberVO memberVO);
	public MemberEntity login(MemberVO memberVO) throws NoMatchMemberInformationException;
	public void changeInfo(MemberEntity oldMember,MemberVO memberVO);
	public void deleteInfo(Long mno);
}
