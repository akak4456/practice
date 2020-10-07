package com.untact.persistent.group;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.untact.demo.UntactenglishstudyApplication;
import com.untact.domain.group.GroupEntity;
import com.untact.vo.PageVO;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UntactenglishstudyApplication.class)
@Transactional
@Commit
@Log
public class GroupEntityRepositoryTest {
	@Autowired
	private GroupEntityRepository repo;
	
	@Before
	public void setUp() {
		repo.deleteAllInBatch();
	}
	
	@Test
	public void initTest() {
		
	}
	
	private static final int MAX_ENTITY_COUNT = 105;
	private static final int EXPECTED_PAGE_COUNT = 11;
	
	@Test
	public void getPageTest() {
		List<GroupEntity> list = generateGroupList();
		specificPageTest(list,1,10);
		specificPageTest(list,11,5);
	}
	
	private List<GroupEntity> generateGroupList(){
		List<GroupEntity> list = new ArrayList<>();
		for(int i=0;i<MAX_ENTITY_COUNT;i++) {
			GroupEntity entity = generateGroupEntity("title"+i);
			list.add(entity);
		}
		repo.saveAll(list);
		return list;
	}
	private GroupEntity generateGroupEntity(String title) {
		return new GroupEntity().builder().title(title).build();
	}
	private void specificPageTest(List<GroupEntity> list,int pageNum,int expectedPageSize) {
		PageVO pageVO = new PageVO(pageNum);
		Page<GroupEntity> page = repo.getPage(pageVO.makePageable(0, "gno"));
		List<GroupEntity> result = page.getContent();
		assertEquals(page.getTotalElements(),MAX_ENTITY_COUNT);
		assertEquals(page.getTotalPages(),EXPECTED_PAGE_COUNT);
		assertEquals(result.size(),expectedPageSize);
		assertEquals(page.getNumber(),pageNum-1);
		int resultIdx = 0;
		for(int i = list.size()-1-pageVO.getSize()*(pageNum-1);i>Math.max(-1,list.size()-1-pageVO.getSize()*pageNum);i--) {
			//list의 첫번째 원소는 가장 처음에, list의 마지막 원소는 가장 나중에 들어옴
			//내림차순으로 정렬되었는지 확인하기 위함
			assertTrue(result.get(resultIdx).getTitle().equals(list.get(i).getTitle()));
			resultIdx++;
		}
	}
}
