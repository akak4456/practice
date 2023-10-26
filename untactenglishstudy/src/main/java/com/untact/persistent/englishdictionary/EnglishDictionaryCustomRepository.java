package com.untact.persistent.englishdictionary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.untact.domain.englishdictionary.EnglishDictionary;

public interface EnglishDictionaryCustomRepository {
	public Page<EnglishDictionary> getPageWithVocabularyNumber(Pageable pageable,Long vno);
}
