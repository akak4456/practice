package com.untact.persistent.board;

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
import com.untact.domain.board.Board;
import com.untact.domain.group.GroupEntity;
import com.untact.persistent.group.GroupEntityRepository;
import com.untact.persistent.groupinclude.GroupIncludeRepository;
import com.untact.persistent.groupwaiting.GroupWaitingRepository;
import com.untact.vo.PageVO;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UntactenglishstudyApplication.class)
@Transactional
@Commit
@Log
public class BoardRepositoryTest {
	@Autowired
	private GroupEntityRepository groupRepo;
	
	@Autowired
	private GroupWaitingRepository groupWaitingRepo;
	
	@Autowired
	private GroupIncludeRepository groupIncludeRepo;
	
	@Autowired
	private BoardRepository repo;
	
	private static final int MAX_ENTITY_COUNT = 105;
	private static final int EXPECTED_PAGE_COUNT = 11;
	
	private GroupEntity group1;
	
	@Before
	public void setUp() {
		repo.deleteAllInBatch();
		
		groupWaitingRepo.deleteAllInBatch();
		groupIncludeRepo.deleteAllInBatch();
		groupRepo.deleteAllInBatch();
		group1 = new GroupEntity().builder().title("title").build();
		groupRepo.save(group1);
	}
	@Test
	public void initTest() {
	}
	
	@Test
	public void getPageWithGroupNumberTest() {
		List<Board> list = generateBoardList(group1);
		specificPageTest(list,group1,1,10);
		specificPageTest(list,group1,11,5);
	}
	
	private List<Board> generateBoardList(GroupEntity group){
		List<Board> list = new ArrayList<>();
		for(int i=0;i<MAX_ENTITY_COUNT;i++) {
			Board entity = generateBoard("title"+i,"content"+i,group);
			list.add(entity);
		}
		repo.saveAll(list);
		return list;
	}
	private Board generateBoard(String title,String content,GroupEntity group) {
		return new Board().builder().title(title).content(content).group(group).build();
	}
	
	private void specificPageTest(List<Board> list,GroupEntity group,int pageNum,int expectedPageSize) {
		PageVO pageVO = new PageVO(pageNum);
		Page<Board> page = repo.getPageWithGroupNumber(pageVO.makePageable(0, "bno"),group.getGno());
		List<Board> result = page.getContent();
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
