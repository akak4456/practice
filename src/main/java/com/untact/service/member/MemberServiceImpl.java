package com.untact.service.member;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.untact.domain.member.MemberEntity;
import com.untact.domain.member.Role;
import com.untact.persistent.member.MemberEntityRepository;
import com.untact.vo.MemberVO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private MemberEntityRepository memberRepo;
	@Override
	public void addMember(MemberVO memberVO) {
		//보안, 입력확인등 별도의 과정 필요함
		MemberEntity entity = MemberEntity.builder()
								.email(memberVO.getEmail())
								.password(passwordEncoder.encode(memberVO.getPassword()))
								.role(Role.MEMBER)
								.build();
		memberRepo.save(entity);
	}
	@Override
	public MemberEntity login(MemberVO memberVO) throws NoMatchMemberInformationException {
		Optional<MemberEntity> entity = memberRepo.findByEmail(memberVO.getEmail());
		if(!entity.isPresent()||!passwordEncoder.matches(memberVO.getPassword(), entity.get().getPassword())) {
			//존재하는 아이디가 없거나 비밀번호가 일치하지 않는다면
			throw new NoMatchMemberInformationException();
		}
		return entity.get();
	}
	@Override
	public void changeInfo(MemberEntity oldMember,MemberVO memberVO) {
		MemberEntity newMember = MemberEntity.builder()
									.mno(oldMember.getMno())
									.email(oldMember.getEmail())
									.password(passwordEncoder.encode(memberVO.getPassword()))
									.role(oldMember.getRole())
									.build();
		//이메일, 역할은 변경 불가(역할은 관리자만이 바꿀 수 있음) 나머지는 바꿀수도 있음
		memberRepo.save(newMember);
	}
	@Override
	public void deleteInfo(Long mno) {
		memberRepo.deleteById(mno);
	}

}
