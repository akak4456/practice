package com.untact.persistent.deposit;

import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.untact.demo.UntactenglishstudyApplication;
import com.untact.domain.deposit.Deposit;
import com.untact.domain.group.GroupEntity;
import com.untact.domain.groupinclude.GroupInclude;
import com.untact.domain.groupinclude.WhichStatus;
import com.untact.domain.member.MemberEntity;
import com.untact.persistent.group.GroupEntityRepository;
import com.untact.persistent.groupinclude.GroupIncludeRepository;
import com.untact.persistent.member.MemberEntityRepository;
import com.untact.persistent.util.DeleteAllUtil;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UntactenglishstudyApplication.class)
@Transactional
@Commit
@Log
public class DepositRepositoryTest {
	@Autowired
	private DeleteAllUtil deleteAllUtil;
	
	@Autowired
	private GroupEntityRepository groupRepo;
	
	@Autowired
	private MemberEntityRepository memberRepo;
	
	@Autowired
	private GroupIncludeRepository groupIncludeRepo;
	
	@Autowired
	private DepositRepository depositRepo;
	
	private GroupEntity group1;
	private GroupEntity group2;
	private GroupEntity group3;
	
	private MemberEntity member1;
	private MemberEntity member2;
	private MemberEntity member3;
	@Before
	public void setUp() {
		deleteAllUtil.deleteAllRepo();
		
		member1 = MemberEntity.builder().email("email1").password("password1").build();
		memberRepo.save(member1);
		member2 = MemberEntity.builder().email("email2").password("password2").build();
		memberRepo.save(member2);
		member3 = MemberEntity.builder().email("email3").password("password3").build();
		memberRepo.save(member3);
		
		group1 = GroupEntity.builder().title("title1").build();
		groupRepo.save(group1);
		group2 = GroupEntity.builder().title("title2").build();
		groupRepo.save(group2);
		group3 = GroupEntity.builder().title("title3").build();
		groupRepo.save(group3);
		
		groupIncludeRepo.save(GroupInclude.builder().group(group1).member(member1).whichStatus(WhichStatus.LEADER).build());
		groupIncludeRepo.save(GroupInclude.builder().group(group1).member(member2).whichStatus(WhichStatus.FOLLOWER).build());
		groupIncludeRepo.save(GroupInclude.builder().group(group1).member(member3).whichStatus(WhichStatus.FOLLOWER).build());
		
		groupIncludeRepo.save(GroupInclude.builder().group(group2).member(member1).whichStatus(WhichStatus.FOLLOWER).build());
		groupIncludeRepo.save(GroupInclude.builder().group(group2).member(member2).whichStatus(WhichStatus.LEADER).build());
		groupIncludeRepo.save(GroupInclude.builder().group(group2).member(member3).whichStatus(WhichStatus.FOLLOWER).build());
	
		groupIncludeRepo.save(GroupInclude.builder().group(group3).member(member1).whichStatus(WhichStatus.FOLLOWER).build());
		groupIncludeRepo.save(GroupInclude.builder().group(group3).member(member2).whichStatus(WhichStatus.FOLLOWER).build());
		groupIncludeRepo.save(GroupInclude.builder().group(group3).member(member3).whichStatus(WhichStatus.LEADER).build());
	
	}
	
	@Test
	public void initTest() {
		
	}
	@Test(expected=NoSuchElementException.class)
	public void findByGroupNumberAndMemberNumberTest() {
		depositRepo.save(Deposit.builder().group(group1).member(member1).build());
		depositRepo.findByGroupNumberAndMemberNumber(group1.getGno(), member1.getMno());
		
		Optional<Deposit> exceptionDeposit = depositRepo.findByGroupNumberAndMemberNumber(group1.getGno(), member2.getMno());//예치금 정보가 없는 회원이 이걸 부른다면?
		exceptionDeposit.get();
		fail();
	}
	/*
	@Test
	public void getPageWithGroupNumberAndMemberNumberTest() {
		List<Deposit> depositList = new ArrayList<>();
		depositSave(depositList,group1,member1,5);
		depositSave(depositList,group1,member2,15);
		depositSave(depositList,group1,member3,25);
		depositSave(depositList,group2,member1,15);
		depositSave(depositList,group2,member2,5);
		//group2에 member3은 예치금을 내지 않았다
		depositSave(depositList,group3,member1,10);
		depositSave(depositList,group3,member2,5);
		depositSave(depositList,group3,member3,12);
		
		depositRepo.saveAll(depositList);
		assertEquals(depositRepo.count(),92L);
		
		specificPageWithGroupNumberAndMemberNumberTest(
				depositList,
				group1,
				member1,
				1,
				5,
				1,
				5,
				Range.of(0, 5)
		);
		specificPageWithGroupNumberAndMemberNumberTest(
				depositList,
				group1,
				member2,
				1,
				15,
				2,
				10,
				Range.of(5, 15)
		);
		specificPageWithGroupNumberAndMemberNumberTest(
				depositList,
				group1,
				member2,
				2,
				15,
				2,
				5,
				Range.of(15, 20)
		);
		specificPageWithGroupNumberAndMemberNumberTest(
				depositList,
				group1,
				member3,
				1,
				25,
				3,
				10,
				Range.of(20, 30)
		);
		specificPageWithGroupNumberAndMemberNumberTest(
				depositList,
				group1,
				member3,
				2,
				25,
				3,
				10,
				Range.of(30, 40)
		);
		specificPageWithGroupNumberAndMemberNumberTest(
				depositList,
				group1,
				member3,
				3,
				25,
				3,
				5,
				Range.of(40, 45)
		);
		specificPageWithGroupNumberAndMemberNumberTest(
				depositList,
				group2,
				member1,
				1,
				15,
				2,
				10,
				Range.of(45, 55)
		);
		specificPageWithGroupNumberAndMemberNumberTest(
				depositList,
				group2,
				member1,
				2,
				15,
				2,
				5,
				Range.of(55, 60)
		);
		specificPageWithGroupNumberAndMemberNumberTest(
				depositList,
				group2,
				member2,
				1,
				5,
				1,
				5,
				Range.of(60, 65)
		);
		specificPageWithGroupNumberAndMemberNumberTest(
				depositList,
				group2,
				member3,
				1,
				0,
				0,
				0,
				Range.of(65, 65)
		);
		specificPageWithGroupNumberAndMemberNumberTest(
				depositList,
				group3,
				member1,
				1,
				10,
				1,
				10,
				Range.of(65, 75)
		);
		specificPageWithGroupNumberAndMemberNumberTest(
				depositList,
				group3,
				member2,
				1,
				5,
				1,
				5,
				Range.of(75, 80)
		);
		specificPageWithGroupNumberAndMemberNumberTest(
				depositList,
				group3,
				member3,
				1,
				12,
				2,
				10,
				Range.of(80, 90)
		);
		specificPageWithGroupNumberAndMemberNumberTest(
				depositList,
				group3,
				member3,
				2,
				12,
				2,
				2,
				Range.of(90, 92)
		);
	}
	private void depositSave(List<Deposit> deposit,GroupEntity group,MemberEntity member,int count) {
		for(long i=1;i<=count;i++) {
			deposit.add(Deposit.builder().group(group).member(member).depositAmount(i*100).build());
		}
	}
	
	private void specificPageWithGroupNumberAndMemberNumberTest(
			List<Deposit> depositList,
			GroupEntity group,
			MemberEntity member,
			int pageNum,
			int expectedTotalElementsCount,
			int expectedTotalPages,
			int expectedPageSize,
			Range range) {
		PageVO pageVO = new PageVO(pageNum);
		Page<Deposit> page = depositRepo.getPageWithGroupNumberAndMemberNumber(pageVO.makePageable(0, "dno"),group.getGno() ,member.getMno());
		List<Deposit> result = page.getContent();
		assertEquals(page.getTotalElements(),expectedTotalElementsCount);
		assertEquals(page.getTotalPages(),expectedTotalPages);
		assertEquals(result.size(),expectedPageSize);
		assertEquals(page.getNumber(),pageNum-1);
		int resultIdx = 0;
		for(int i=range.getEnd()-1;i>=range.getStart();i--) {
			assertTrue(result.get(resultIdx).getGroup().getGno().equals(depositList.get(i).getGroup().getGno()));
			assertTrue(result.get(resultIdx).getMember().getMno().equals(depositList.get(i).getMember().getMno()));
			resultIdx++;
		}
	}
	*/
}
