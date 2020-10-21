package com.untact.persistent.position;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
import com.untact.domain.member.MemberEntity;
import com.untact.domain.position.PositionEntity;
import com.untact.domain.position.WhichPosition;
import com.untact.persistent.group.GroupEntityRepository;
import com.untact.persistent.member.MemberEntityRepository;
import com.untact.persistent.util.DeleteAllUtil;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UntactenglishstudyApplication.class)
@Transactional
@Commit
@Log
public class PositionEntityRepositoryTest {
	@Autowired
	private DeleteAllUtil deleteAllUtil;
	
	@Autowired
	private MemberEntityRepository memberRepo;
	
	@Autowired
	private GroupEntityRepository groupRepo;
	
	@Autowired
	private PositionEntityRepository positionRepo;
	
	private MemberEntity member1;
	
	private MemberEntity member2;
	
	private MemberEntity member3;
	
	private GroupEntity group1;
	
	private GroupEntity group2;
	
	@Before
	public void setUp() {
		deleteAllUtil.deleteAllRepo();
		
		member1 = new MemberEntity().builder().email("email1").password("password1").build();
		memberRepo.save(member1);
		member2 = new MemberEntity().builder().email("email2").password("password2").build();
		memberRepo.save(member2);
		member3 = new MemberEntity().builder().email("email3").password("password3").build();
		memberRepo.save(member3);

		group1 = new GroupEntity().builder().title("title1").build();
		groupRepo.save(group1);
		group2 = new GroupEntity().builder().title("title2").build();
		groupRepo.save(group2);
		
		positionRepo.save(new PositionEntity().builder().group(group1).member(member1).whichPosition(WhichPosition.Leader).build());
		positionRepo.save(new PositionEntity().builder().group(group1).member(member2).whichPosition(WhichPosition.Follower).build());
		positionRepo.save(new PositionEntity().builder().group(group1).member(member3).whichPosition(WhichPosition.Follower).build());
	
		positionRepo.save(new PositionEntity().builder().group(group2).member(member1).whichPosition(WhichPosition.Follower).build());
		positionRepo.save(new PositionEntity().builder().group(group2).member(member2).whichPosition(WhichPosition.Leader).build());
		positionRepo.save(new PositionEntity().builder().group(group2).member(member3).whichPosition(WhichPosition.Follower).build());
	}
	
	@Test
	public void initTest() {
		
	}
	
	@Test
	public void findByGroupAndMemberAndWhichPositionTest() {
		assertTrue(positionRepo.findByGroupAndMemberAndWhichPosition(group1,member1, WhichPosition.Leader).isPresent());
		assertFalse(positionRepo.findByGroupAndMemberAndWhichPosition(group1,member2, WhichPosition.Leader).isPresent());
		assertFalse(positionRepo.findByGroupAndMemberAndWhichPosition(group1,member3, WhichPosition.Leader).isPresent());
		
		assertFalse(positionRepo.findByGroupAndMemberAndWhichPosition(group2,member1, WhichPosition.Leader).isPresent());
		assertTrue(positionRepo.findByGroupAndMemberAndWhichPosition(group2,member2, WhichPosition.Leader).isPresent());
		assertFalse(positionRepo.findByGroupAndMemberAndWhichPosition(group2,member3, WhichPosition.Leader).isPresent());
		
		assertFalse(positionRepo.findByGroupAndMemberAndWhichPosition(group1,member1, WhichPosition.Follower).isPresent());
		assertTrue(positionRepo.findByGroupAndMemberAndWhichPosition(group1,member2, WhichPosition.Follower).isPresent());
		assertTrue(positionRepo.findByGroupAndMemberAndWhichPosition(group1,member3, WhichPosition.Follower).isPresent());
		
		assertTrue(positionRepo.findByGroupAndMemberAndWhichPosition(group2,member1, WhichPosition.Follower).isPresent());
		assertFalse(positionRepo.findByGroupAndMemberAndWhichPosition(group2,member2, WhichPosition.Follower).isPresent());
		assertTrue(positionRepo.findByGroupAndMemberAndWhichPosition(group2,member3, WhichPosition.Follower).isPresent());
		
	}
}
