package com.untact.persistent.englishdictionary;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.untact.domain.englishdictionary.EnglishDictionary;

public class EnglishDictionaryCustomRepositoryImpl extends QuerydslRepositorySupport
		implements EnglishDictionaryCustomRepository {	
	public EnglishDictionaryCustomRepositoryImpl() {
		super(EnglishDictionary.class);
	}
}
