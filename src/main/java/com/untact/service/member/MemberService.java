package com.untact.service.member;

import com.untact.domain.member.MemberEntity;
import com.untact.vo.MemberVO;

public interface MemberService {
	//security framework 적용 전 임시 인터페이스임
	//수정 가능성 다분함
	public void addMember(MemberVO memberVO);
	
	//이건 임시적인 함수
	//절대로 클라이언트에게 회원 비밀번호 정보를 넘기지 말것!!!!!!
	public MemberEntity login(MemberVO memberVO) throws NoMatchMemberInformationException;
}
