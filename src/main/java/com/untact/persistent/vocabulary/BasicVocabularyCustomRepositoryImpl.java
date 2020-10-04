package com.untact.persistent.vocabulary;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.untact.domain.vocabulary.BasicVocabulary;

public class BasicVocabularyCustomRepositoryImpl extends QuerydslRepositorySupport
		implements BasicVocabularyCustomRepository {
	public BasicVocabularyCustomRepositoryImpl() {
		super(BasicVocabulary.class);
	}
}
