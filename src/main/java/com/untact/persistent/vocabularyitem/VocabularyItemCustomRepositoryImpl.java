package com.untact.persistent.vocabularyitem;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.JPQLQuery;
import com.untact.domain.englishspelling.EnglishSpelling;
import com.untact.domain.vocabularyitem.QVocabularyItem;
import com.untact.domain.vocabularyitem.VocabularyItem;

public class VocabularyItemCustomRepositoryImpl extends QuerydslRepositorySupport
		implements VocabularyItemCustomRepository {
	public VocabularyItemCustomRepositoryImpl() {
		super(VocabularyItem.class);
	}

	@Override
	public List<EnglishSpelling> findEnglishSpellingByVocaburaryNumber(Long vno) {
		QVocabularyItem item = QVocabularyItem.vocabularyItem;
		JPQLQuery<EnglishSpelling> query = from(item).select(item.englishSpelling);
		query.where(item.vocabulary.vno.eq(vno));
		return query.fetch();
	}
}
