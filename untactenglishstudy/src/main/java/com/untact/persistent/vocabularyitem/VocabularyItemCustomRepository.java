package com.untact.persistent.vocabularyitem;

import java.util.List;

import com.untact.domain.englishspelling.EnglishSpelling;

public interface VocabularyItemCustomRepository {
	public List<EnglishSpelling> findEnglishSpellingByVocaburaryNumber(Long vno);
}
