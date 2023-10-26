package com.untact.service.group;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.untact.persistent.group.GroupEntityRepository;

@RunWith(MockitoJUnitRunner.class)
public class GroupServiceImplTest {
	@Mock
	private GroupEntityRepository groupRepo;
	@InjectMocks
	private GroupServiceImpl groupService;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void dismissGroupManualTest() {
//		GroupEntity group1 = new GroupEntity().builder().gno(1L).title("title1").build();
//		GroupEntity group2 = new GroupEntity().builder().gno(2L).title("title2").build();
//		MemberEntity member1 = new MemberEntity().builder().email("email1").password("password1").build();
//		MemberEntity member2 = new MemberEntity().builder().email("email2").password("password2").build();
//		MemberEntity member3 = new MemberEntity().builder().email("email3").password("password3").build();
//		when(positionRepo.findByMemberEntity(ArgumentMatchers.refEq(member1))).thenReturn(Optional.empty());
//		when(positionRepo.findByMemberEntity(ArgumentMatchers.refEq(member2))).thenReturn(
//				Optional.of(
//						new PositionEntity().builder().
//						group(group2).
//						member(member2).
//						whichPosition(WhichPosition.Leader).build()
//				)
//		);
//		when(positionRepo.findByMemberEntity(ArgumentMatchers.refEq(member3))).thenReturn(
//				Optional.of(
//						new PositionEntity().builder().
//						group(group1).
//						member(member3).
//						whichPosition(WhichPosition.Follower).build()
//				)
//		);
//		assertFalse(groupService.dismissGroupManual(1L, member1));
//		assertFalse(groupService.dismissGroupManual(1L, member3));
//		assertFalse(groupService.dismissGroupManual(2L, member3));
//		assertFalse(groupService.dismissGroupManual(1L, member2));
//		assertTrue(groupService.dismissGroupManual(2L, member2));
	}
	
}
