package com.untact.persistent.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.untact.domain.englishdictionary.EnglishDictionary;
import com.untact.domain.englishspelling.EnglishSpelling;
import com.untact.persistent.englishdictionary.EnglishDictionaryRepository;
import com.untact.persistent.englishspelling.EnglishSpellingRepository;

@Component
public class DefaultEnglishSpellingAndDictionaryUtil {
	@Autowired
	private EnglishSpellingRepository englishSpellingRepo;
	
	@Autowired
	private EnglishDictionaryRepository englishDictionaryRepo;
	
	public void setUpDefaultDictionary() {
		List<EnglishSpelling> spellingList = new ArrayList<>();
		spellingList.add(EnglishSpelling.builder().spelling("angry").build());
		spellingList.add(EnglishSpelling.builder().spelling("apple").build());
		spellingList.add(EnglishSpelling.builder().spelling("banana").build());
		spellingList.add(EnglishSpelling.builder().spelling("beautiful").build());
		spellingList.add(EnglishSpelling.builder().spelling("sorry").build());
		englishSpellingRepo.saveAll(spellingList);
		List<EnglishDictionary> dictionaryList = new ArrayList<>();
		dictionaryList.add(EnglishDictionary.builder().englishSpelling(spellingList.get(0)).meaning("화난").build());
		dictionaryList.add(EnglishDictionary.builder().englishSpelling(spellingList.get(0)).meaning("분개한").build());
		dictionaryList.add(EnglishDictionary.builder().englishSpelling(spellingList.get(1)).meaning("사과").build());
		dictionaryList.add(EnglishDictionary.builder().englishSpelling(spellingList.get(2)).meaning("바나나").build());
		dictionaryList.add(EnglishDictionary.builder().englishSpelling(spellingList.get(3)).meaning("수려한").build());
		dictionaryList.add(EnglishDictionary.builder().englishSpelling(spellingList.get(3)).meaning("아름다운").build());
		dictionaryList.add(EnglishDictionary.builder().englishSpelling(spellingList.get(4)).meaning("미안한").build());
		englishDictionaryRepo.saveAll(dictionaryList);
	}
}
