package com.untact.persistent.groupinclude;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
import com.untact.domain.groupinclude.GroupInclude;
import com.untact.domain.groupinclude.WhichStatus;
import com.untact.domain.member.MemberEntity;
import com.untact.persistent.group.GroupEntityRepository;
import com.untact.persistent.member.MemberEntityRepository;
import com.untact.persistent.util.DeleteAllUtil;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UntactenglishstudyApplication.class)
@Commit
@Log
public class GroupIncludeRepositoryTest {
	@Autowired
	private DeleteAllUtil deleteAllUtil;
	@Autowired
	private MemberEntityRepository memberRepo;
	@Autowired
	private GroupEntityRepository groupRepo;
	@Autowired
	private GroupIncludeRepository groupIncludeRepo;
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
	}
	@Test
	public void initTest() {
	
	}
	
	@Test
	public void changeStatusTest() {
		GroupInclude include = new GroupInclude().builder().group(group1).member(member1).whichStatus(WhichStatus.WAITING).build();
		groupIncludeRepo.save(include);
		Long gino = include.getGino();
		assertEquals(groupIncludeRepo.findById(gino).get().getWhichStatus(),WhichStatus.WAITING);
		groupIncludeRepo.updateStatusByGroupIncludeNumber(WhichStatus.FOLLOWER, gino);
		assertEquals(groupIncludeRepo.findById(gino).get().getWhichStatus(),WhichStatus.FOLLOWER);
		groupIncludeRepo.updateStatusByGroupIncludeNumber(WhichStatus.REJECT, gino);
		assertEquals(groupIncludeRepo.findById(gino).get().getWhichStatus(),WhichStatus.REJECT);
	}
	
	@Test
	public void findByGroupAndMemberAndWhichStatusTest() {
		
		groupIncludeRepo.save(new GroupInclude().builder().group(group1).member(member1).whichStatus(WhichStatus.LEADER).build());
		groupIncludeRepo.save(new GroupInclude().builder().group(group1).member(member2).whichStatus(WhichStatus.FOLLOWER).build());
		groupIncludeRepo.save(new GroupInclude().builder().group(group1).member(member3).whichStatus(WhichStatus.FOLLOWER).build());
	
		groupIncludeRepo.save(new GroupInclude().builder().group(group2).member(member1).whichStatus(WhichStatus.FOLLOWER).build());
		groupIncludeRepo.save(new GroupInclude().builder().group(group2).member(member2).whichStatus(WhichStatus.LEADER).build());
		groupIncludeRepo.save(new GroupInclude().builder().group(group2).member(member3).whichStatus(WhichStatus.FOLLOWER).build());
		
		assertTrue(groupIncludeRepo.findByGroupNumberAndMemberNumberAndWhichStatus(group1.getGno(),member1.getMno(), WhichStatus.LEADER).isPresent());
		assertFalse(groupIncludeRepo.findByGroupNumberAndMemberNumberAndWhichStatus(group1.getGno(),member2.getMno(), WhichStatus.LEADER).isPresent());
		assertFalse(groupIncludeRepo.findByGroupNumberAndMemberNumberAndWhichStatus(group1.getGno(),member3.getMno(), WhichStatus.LEADER).isPresent());
		
		assertFalse(groupIncludeRepo.findByGroupNumberAndMemberNumberAndWhichStatus(group2.getGno(),member1.getMno(), WhichStatus.LEADER).isPresent());
		assertTrue(groupIncludeRepo.findByGroupNumberAndMemberNumberAndWhichStatus(group2.getGno(),member2.getMno(), WhichStatus.LEADER).isPresent());
		assertFalse(groupIncludeRepo.findByGroupNumberAndMemberNumberAndWhichStatus(group2.getGno(),member3.getMno(), WhichStatus.LEADER).isPresent());
		
		assertFalse(groupIncludeRepo.findByGroupNumberAndMemberNumberAndWhichStatus(group1.getGno(),member1.getMno(), WhichStatus.FOLLOWER).isPresent());
		assertTrue(groupIncludeRepo.findByGroupNumberAndMemberNumberAndWhichStatus(group1.getGno(),member2.getMno(), WhichStatus.FOLLOWER).isPresent());
		assertTrue(groupIncludeRepo.findByGroupNumberAndMemberNumberAndWhichStatus(group1.getGno(),member3.getMno(), WhichStatus.FOLLOWER).isPresent());
		
		assertTrue(groupIncludeRepo.findByGroupNumberAndMemberNumberAndWhichStatus(group2.getGno(),member1.getMno(), WhichStatus.FOLLOWER).isPresent());
		assertFalse(groupIncludeRepo.findByGroupNumberAndMemberNumberAndWhichStatus(group2.getGno(),member2.getMno(), WhichStatus.FOLLOWER).isPresent());
		assertTrue(groupIncludeRepo.findByGroupNumberAndMemberNumberAndWhichStatus(group2.getGno(),member3.getMno(), WhichStatus.FOLLOWER).isPresent());
	}
	
}
