package com.untact.persistent.englishspelling;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.untact.domain.englishspelling.EnglishSpelling;

public class EnglishSpellingCustomRepositoryImpl extends QuerydslRepositorySupport
		implements EnglishSpellingCustomRepository {
	public EnglishSpellingCustomRepositoryImpl() {
		super(EnglishSpelling.class);
	}
}
