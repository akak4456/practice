package com.untact.service.groupwaiting;

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

import com.untact.domain.group.GroupEntity;
import com.untact.domain.groupwaiting.GroupWaiting;
import com.untact.domain.groupwaiting.GroupWaitingStatus;
import com.untact.domain.member.MemberEntity;
import com.untact.persistent.group.GroupEntityRepository;
import com.untact.persistent.groupwaiting.GroupWaitingRepository;
import com.untact.persistent.member.MemberEntityRepository;
import com.untact.vo.GroupWaitingVO;

@RunWith(MockitoJUnitRunner.class)
public class GroupWaitingServiceImplTest {
	@Mock
	private MemberEntityRepository memberRepo;
	@Mock
	private GroupEntityRepository groupRepo;
	@Mock
	private GroupWaitingRepository groupWaitingRepo;
	
	private GroupWaitingServiceImpl groupWaitingService;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		groupWaitingService = new GroupWaitingServiceImpl(memberRepo,groupRepo,groupWaitingRepo);
	}
	
	@Test
	public void requestJoinTest() {
		Optional<MemberEntity> memberEntity = Optional.of(MemberEntity.builder().mno(200L).email("email").password("password").build());
		when(memberRepo.findById(200L)).thenReturn(memberEntity);
		Optional<GroupEntity> groupEntity = Optional.of(GroupEntity.builder().gno(100L).title("title").build());
		when(groupRepo.findById(100L)).thenReturn(groupEntity);
		GroupWaitingVO groupWaitingVO = new GroupWaitingVO();
		groupWaitingVO.setGno(100L);
		groupWaitingVO.setMno(200L);
		groupWaitingService.requestJoin(groupWaitingVO);
		GroupWaiting entity = GroupWaiting.builder().group(groupEntity.get()).member(memberEntity.get()).status(GroupWaitingStatus.WAIT).build();
		verify(groupWaitingRepo,times(1)).save(entity);
	}
	
	@Test
	public void acceptJoinTest() {
		groupWaitingService.acceptJoin(1L);
		verify(groupWaitingRepo,times(1)).changeStatus(GroupWaitingStatus.ACCEPT, 1L);
	}
	
	@Test
	public void rejectJoinTest() {
		groupWaitingService.rejectJoin(1L);
		verify(groupWaitingRepo,times(1)).changeStatus(GroupWaitingStatus.REJECT, 1L);
	}
}
