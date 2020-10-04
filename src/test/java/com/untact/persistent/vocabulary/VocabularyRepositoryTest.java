package com.untact.persistent.vocabulary;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.untact.demo.UntactenglishstudyApplication;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UntactenglishstudyApplication.class)
@Transactional
@Commit
@Log
public class VocabularyRepositoryTest {
	@Autowired
	private VocabularyRepository repo;
	
	@Before
	public void setUp() {
		repo.deleteAllInBatch();
	}
	
	@Test
	public void initTest() {
		
	}
}
