package com.untact.service.member;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.untact.domain.member.MemberEntity;
import com.untact.persistent.member.MemberEntityRepository;
import com.untact.vo.MemberVO;

@RunWith(MockitoJUnitRunner.class)
public class MemberServiceImplTest {
	@Mock
	private MemberEntityRepository memberRepo;
	
	private MemberServiceImpl memberService;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		memberService = new MemberServiceImpl(memberRepo);
	}
	
	@Test
	public void addMemberTest() {
		MemberVO memberVO = new MemberVO();
		memberVO.setEmail("akak4456@naver.com");
		memberVO.setPassword("1234");
		memberService.addMember(memberVO);
		MemberEntity memberEntity = MemberEntity.builder().email(memberVO.getEmail()).password(memberVO.getPassword()).build();
		verify(memberRepo,times(1)).save(memberEntity);
	}
	
	@Test
	public void loginTest() {
		Optional<MemberEntity> entity = Optional.of(new MemberEntity().builder().email("akak4456@naver.com").password("1234").build());
		when(memberRepo.findByEmail("akak4456@naver.com")).thenReturn(entity);
		MemberVO memberVO = new MemberVO();
		memberVO.setEmail("akak4456@naver.com");
		memberVO.setPassword("1234");
		loginSuccess(memberVO);
		memberVO.setEmail("akak4456@naver.com");
		memberVO.setPassword("5678");
		loginFail(memberVO);
		memberVO.setEmail("akak4478@naver.com");
		memberVO.setPassword("1234");
		loginFail(memberVO);
	}
	private void loginSuccess(MemberVO memberVO) {
		try {
			memberService.login(memberVO);
		}catch(NoMatchMemberInformationException e) {
			fail();
		}
	}
	private void loginFail(MemberVO memberVO) {
		try {
			memberService.login(memberVO);
			fail();
		}catch(NoMatchMemberInformationException e) {
			
		}
	}
}
