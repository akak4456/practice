package com.untact.persistent.groupwaiting;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.untact.demo.UntactenglishstudyApplication;
import com.untact.domain.group.GroupEntity;
import com.untact.domain.groupwaiting.GroupWaiting;
import com.untact.domain.groupwaiting.GroupWaitingStatus;
import com.untact.domain.member.MemberEntity;
import com.untact.persistent.group.GroupEntityRepository;
import com.untact.persistent.member.MemberEntityRepository;
import com.untact.persistent.util.DeleteAllUtil;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UntactenglishstudyApplication.class)
@Log
@Commit
public class GroupWaitingRepositoryTest {
	@Autowired
	private DeleteAllUtil deleteAllUtil;
	@Autowired
	private MemberEntityRepository memberRepo;
	@Autowired
	private GroupEntityRepository groupRepo;
	@Autowired
	private GroupWaitingRepository groupWaitingRepo;
	
	@Before
	public void setUp() {
		deleteAllUtil.deleteAllRepo();
	}
	
	@Test
	public void initTest() {
		
	}
	@Test
	public void changeStatusTest() {
		MemberEntity member = MemberEntity.builder().email("email").password("password").build();
		memberRepo.save(member);
		GroupEntity group = GroupEntity.builder().title("title").build();
		groupRepo.save(group);
		GroupWaiting waiting = GroupWaiting.builder().group(group).member(member).status(GroupWaitingStatus.WAIT).build();
		groupWaitingRepo.save(waiting);
		Long gwno = waiting.getGwno();
		assertEquals(groupWaitingRepo.findById(gwno).get().getStatus(),GroupWaitingStatus.WAIT);
		groupWaitingRepo.changeStatus(GroupWaitingStatus.ACCEPT, gwno);
		assertEquals(groupWaitingRepo.findById(gwno).get().getStatus(),GroupWaitingStatus.ACCEPT);
		groupWaitingRepo.changeStatus(GroupWaitingStatus.REJECT, gwno);
		assertEquals(groupWaitingRepo.findById(gwno).get().getStatus(),GroupWaitingStatus.REJECT);
	}
}
