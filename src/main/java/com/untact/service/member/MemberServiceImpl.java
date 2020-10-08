package com.untact.service.member;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.untact.domain.member.MemberEntity;
import com.untact.persistent.member.MemberEntityRepository;
import com.untact.vo.MemberVO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberEntityRepository memberRepo;
	@Override
	public void addMember(MemberVO memberVO) {
		//보안, 입력확인등 별도의 과정 필요함
		MemberEntity entity = MemberEntity.builder()
								.email(memberVO.getEmail())
								.password(memberVO.getPassword())
								.build();
		memberRepo.save(entity);
	}
	@Override
	public MemberEntity login(MemberVO memberVO) throws NoMatchMemberInformationException {
		Optional<MemberEntity> entity = memberRepo.findByEmail(memberVO.getEmail());
		if(!entity.isPresent()||!entity.get().getPassword().equals(memberVO.getPassword())) {
			//존재하는 아이디가 없거나 비밀번호가 일치하지 않는다면
			throw new NoMatchMemberInformationException();
		}
		return entity.get();
	}

}
