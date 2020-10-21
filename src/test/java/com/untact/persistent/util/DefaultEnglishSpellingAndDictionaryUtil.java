package com.untact.persistent.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.untact.domain.englishdictionary.EnglishDictionary;
import com.untact.domain.englishdictionary.EnglishDictionaryId;
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
		spellingList.add(new EnglishSpelling().builder().spelling("angry").build());
		spellingList.add(new EnglishSpelling().builder().spelling("apple").build());
		spellingList.add(new EnglishSpelling().builder().spelling("banana").build());
		spellingList.add(new EnglishSpelling().builder().spelling("beautiful").build());
		spellingList.add(new EnglishSpelling().builder().spelling("sorry").build());
		englishSpellingRepo.saveAll(spellingList);
		List<EnglishDictionaryId> idList = new ArrayList<>();
		idList.add(new EnglishDictionaryId(spellingList.get(0).getSpelling(),"화난"));
		idList.add(new EnglishDictionaryId(spellingList.get(0).getSpelling(),"분개한"));
		idList.add(new EnglishDictionaryId(spellingList.get(1).getSpelling(),"사과"));
		idList.add(new EnglishDictionaryId(spellingList.get(2).getSpelling(),"바나나"));
		idList.add(new EnglishDictionaryId(spellingList.get(3).getSpelling(),"수려한"));
		idList.add(new EnglishDictionaryId(spellingList.get(3).getSpelling(),"아름다운"));
		idList.add(new EnglishDictionaryId(spellingList.get(4).getSpelling(),"미안한"));
		List<EnglishDictionary> dictionaryList = new ArrayList<>();
		dictionaryList.add(new EnglishDictionary().builder().id(idList.get(0)).englishSpelling(spellingList.get(0)).build());
		dictionaryList.add(new EnglishDictionary().builder().id(idList.get(1)).englishSpelling(spellingList.get(0)).build());
		dictionaryList.add(new EnglishDictionary().builder().id(idList.get(2)).englishSpelling(spellingList.get(1)).build());
		dictionaryList.add(new EnglishDictionary().builder().id(idList.get(3)).englishSpelling(spellingList.get(2)).build());
		dictionaryList.add(new EnglishDictionary().builder().id(idList.get(4)).englishSpelling(spellingList.get(3)).build());
		dictionaryList.add(new EnglishDictionary().builder().id(idList.get(5)).englishSpelling(spellingList.get(3)).build());
		dictionaryList.add(new EnglishDictionary().builder().id(idList.get(6)).englishSpelling(spellingList.get(4)).build());
		englishDictionaryRepo.saveAll(dictionaryList);
	}
}
