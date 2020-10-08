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

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UntactenglishstudyApplication.class)
@Log
@Commit
public class GroupWaitingRepositoryTest {
	@Autowired
	private MemberEntityRepository memberRepo;
	@Autowired
	private GroupEntityRepository groupRepo;
	@Autowired
	private GroupWaitingRepository repo;
	
	@Before
	public void setUp() {
		repo.deleteAllInBatch();
		memberRepo.deleteAllInBatch();
		groupRepo.deleteAllInBatch();
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
		repo.save(waiting);
		Long gwno = waiting.getGwno();
		assertEquals(repo.findById(gwno).get().getStatus(),GroupWaitingStatus.WAIT);
		repo.changeStatus(GroupWaitingStatus.ACCEPT, gwno);
		assertEquals(repo.findById(gwno).get().getStatus(),GroupWaitingStatus.ACCEPT);
		repo.changeStatus(GroupWaitingStatus.REJECT, gwno);
		assertEquals(repo.findById(gwno).get().getStatus(),GroupWaitingStatus.REJECT);
	}
}
