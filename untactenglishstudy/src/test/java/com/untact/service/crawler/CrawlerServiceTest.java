package com.untact.service.crawler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.untact.demo.UntactenglishstudyApplication;
import com.untact.domain.antonym.Antonym;
import com.untact.domain.englishdictionary.EnglishDictionary;
import com.untact.domain.thesaurus.Thesaurus;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UntactenglishstudyApplication.class)
@Transactional
@Commit
@Log
public class CrawlerServiceTest {
	@Autowired
	private CrawlerService crawlerService;
	
	//@Test
	public void getMeaningTest() {
		Set<String> notExistWord = new HashSet<>();
		notExistWord.add("apple");
		notExistWord.add("ad");
		List<EnglishDictionary> meaning = crawlerService.getMeaning(notExistWord);
		log.info(meaning.get(0).getMeaning());
		log.info(meaning.get(0).getPartOfSpeech());
		log.info(meaning.get(1).getMeaning());
		log.info(meaning.get(1).getPartOfSpeech());
	}
	
	//@Test
	public void getThesaurusTest() {
		Set<String> notExistWord = new HashSet<>();
		notExistWord.add("abide");
		List<Thesaurus> thesaurus = crawlerService.getThesaurus(notExistWord);
		log.info(thesaurus.get(0).getWordto());
		log.info(thesaurus.get(1).getWordto());
		log.info(thesaurus.get(2).getWordto());
	}
	
	//@Test
	public void getAntonymTest() {
		Set<String> notExistWord = new HashSet<>();
		notExistWord.add("abbreviate");
		List<Antonym> antonym = crawlerService.getAntonym(notExistWord);
		log.info(antonym.get(0).getWordto());
	}
}
