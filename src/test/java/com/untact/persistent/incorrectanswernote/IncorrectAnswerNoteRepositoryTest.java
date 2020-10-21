package com.untact.persistent.incorrectanswernote;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.untact.demo.UntactenglishstudyApplication;
import com.untact.persistent.util.DeleteAllUtil;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UntactenglishstudyApplication.class)
@Log
@Commit
public class IncorrectAnswerNoteRepositoryTest {
	@Autowired
	private DeleteAllUtil deleteAllUtil;
	
	@Before
	public void setUp() {
		deleteAllUtil.deleteAllRepo();
	}
	
	@Test
	public void initTest() {
		
	}
}
