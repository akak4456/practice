package com.untact.service.crawler;

import java.util.List;
import java.util.Set;

import com.untact.domain.antonym.Antonym;
import com.untact.domain.englishdictionary.EnglishDictionary;
import com.untact.domain.thesaurus.Thesaurus;

public interface CrawlerService {
	public List<EnglishDictionary> getMeaning(Set<String> notExistWord);
	public List<Thesaurus> getThesaurus(Set<String> notExistWord);
	public List<Antonym> getAntonym(Set<String> notExistWord);
}
