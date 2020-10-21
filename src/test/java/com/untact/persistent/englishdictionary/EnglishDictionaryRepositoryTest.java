package com.untact.persistent.englishdictionary;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.untact.demo.UntactenglishstudyApplication;
import com.untact.domain.englishdictionary.EnglishDictionary;
import com.untact.domain.englishdictionary.EnglishDictionaryId;
import com.untact.domain.englishspelling.EnglishSpelling;
import com.untact.persistent.englishspelling.EnglishSpellingRepository;
import com.untact.persistent.util.DeleteAllUtil;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UntactenglishstudyApplication.class)
@Transactional
@Commit
@Log
public class EnglishDictionaryRepositoryTest {
	@Autowired
	private DeleteAllUtil deleteAllUtil;
	
	@Autowired
	private EnglishDictionaryRepository englishDictionaryRepo;
	
	@Autowired
	private EnglishSpellingRepository englishSpellingRepo;
	@Before
	public void setUp() {
		deleteAllUtil.deleteAllRepo();
	}
	
	@Test
	public void initTest() {
		
	}
	
	@Test
	public void insertTest() {
		EnglishSpelling spell = new EnglishSpelling().builder().spelling("a").build();
		englishSpellingRepo.save(spell);
		
		EnglishDictionaryId id1 = new EnglishDictionaryId(spell.getSpelling(),"가나다");
		EnglishDictionaryId id2 = new EnglishDictionaryId(spell.getSpelling(),"마바사");
		englishDictionaryRepo.save(new EnglishDictionary().builder().id(id1).englishSpelling(spell).build());
		englishDictionaryRepo.save(new EnglishDictionary().builder().id(id2).englishSpelling(spell).build());
		assertEquals(englishDictionaryRepo.count(),2L);
	}
	
	@Test
	public void insertDuplicateTest() {
		EnglishSpelling spell = new EnglishSpelling().builder().spelling("a").build();
		englishSpellingRepo.save(spell);
		
		EnglishDictionaryId id1 = new EnglishDictionaryId(spell.getSpelling(),"가나다");
		englishDictionaryRepo.save(new EnglishDictionary().builder().id(id1).englishSpelling(spell).build());
		englishDictionaryRepo.save(new EnglishDictionary().builder().id(id1).englishSpelling(spell).build());
		englishDictionaryRepo.save(new EnglishDictionary().builder().id(id1).englishSpelling(spell).build());
		assertEquals(englishDictionaryRepo.count(),1L);
	}
}
