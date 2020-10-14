package com.untact.persistent.board;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.untact.demo.UntactenglishstudyApplication;
import com.untact.domain.board.Board;
import com.untact.domain.group.GroupEntity;
import com.untact.persistent.group.GroupEntityRepository;
import com.untact.persistent.groupinclude.GroupIncludeRepository;
import com.untact.persistent.groupwaiting.GroupWaitingRepository;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UntactenglishstudyApplication.class)
@Transactional
@Commit
@Log
public class GenerateBoardData {
	@Autowired
	private GroupEntityRepository groupRepo;
	
	@Autowired
	private GroupWaitingRepository groupWaitingRepo;
	
	@Autowired
	private GroupIncludeRepository groupIncludeRepo;
	
	@Autowired
	private BoardRepository repo;
	
	private GroupEntity group1;
	
	@Test
	public void generateBoardData() {
		repo.deleteAllInBatch();
		
		groupWaitingRepo.deleteAllInBatch();
		groupIncludeRepo.deleteAllInBatch();
		groupRepo.deleteAllInBatch();
		group1 = new GroupEntity().builder().title("title").build();
		groupRepo.save(group1);
		
		List<Board> list = new ArrayList<>();
		for(int i=0;i<110;i++) {
			Board entity = new Board().builder().title("title"+i).content("content"+i).group(group1).build();
			list.add(entity);
		}
		repo.saveAll(list);
	}
}
