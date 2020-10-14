package com.untact.persistent.reply;

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
import com.untact.domain.reply.Reply;
import com.untact.persistent.board.BoardRepository;
import com.untact.persistent.group.GroupEntityRepository;
import com.untact.persistent.groupinclude.GroupIncludeRepository;
import com.untact.persistent.groupwaiting.GroupWaitingRepository;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UntactenglishstudyApplication.class)
@Transactional
@Commit
@Log
public class GenerateReplyData {
	@Autowired
	private GroupEntityRepository groupRepo;
	
	@Autowired
	private GroupWaitingRepository groupWaitingRepo;
	
	@Autowired
	private GroupIncludeRepository groupIncludeRepo;
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private ReplyRepository repo;
	
	private GroupEntity group1;
	
	private Board board1;
	
	private static final int MAX_ENTITY_COUNT = 105;
	private static final int EXPECTED_PAGE_COUNT = 11;
	
	@Test
	public void generateReplyData() {
		repo.deleteAllInBatch();
		
		boardRepo.deleteAllInBatch();
		
		groupWaitingRepo.deleteAllInBatch();
		groupIncludeRepo.deleteAllInBatch();
		groupRepo.deleteAllInBatch();
		
		group1 = new GroupEntity().builder().title("title").build();
		groupRepo.save(group1);
		
		board1 = new Board().builder().title("title").content("content").build();
		boardRepo.save(board1);
		
		List<Reply> list = new ArrayList<>();
		for(int i=0;i<MAX_ENTITY_COUNT;i++) {
			Reply entity = new Reply().builder().message("message"+i).group(group1).board(board1).build();
			list.add(entity);
		}
		repo.saveAll(list);
	}
}
