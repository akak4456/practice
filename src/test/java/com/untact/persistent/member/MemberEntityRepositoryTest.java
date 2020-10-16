package com.untact.persistent.member;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.untact.demo.UntactenglishstudyApplication;
import com.untact.persistent.groupwaiting.GroupWaitingRepository;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UntactenglishstudyApplication.class)
@Transactional
@Commit
@Log
public class MemberEntityRepositoryTest {
	@Autowired
	private GroupWaitingRepository groupWaitingRepo;
	
	@Autowired
	private MemberEntityRepository repo;
	
	@Before
	public void setUp() {
		groupWaitingRepo.deleteAllInBatch();
		repo.deleteAllInBatch();
	}
	
	@Test
	public void initTest() {
		
	}
}
