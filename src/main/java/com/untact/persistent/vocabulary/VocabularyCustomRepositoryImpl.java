package com.untact.persistent.vocabulary;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.untact.domain.vocabulary.Vocabulary;

public class VocabularyCustomRepositoryImpl extends QuerydslRepositorySupport implements VocabularyCustomRepository {
	public VocabularyCustomRepositoryImpl() {
		super(Vocabulary.class);
	}
}
