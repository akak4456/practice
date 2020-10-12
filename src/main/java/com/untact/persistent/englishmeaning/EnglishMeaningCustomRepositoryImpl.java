package com.untact.persistent.englishmeaning;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.untact.domain.englishmeaning.EnglishMeaning;

public class EnglishMeaningCustomRepositoryImpl extends QuerydslRepositorySupport
		implements EnglishMeaningCustomRepository {	
	public EnglishMeaningCustomRepositoryImpl() {
		super(EnglishMeaning.class);
	}
}
