package com.untact.persistent.phrase;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.untact.domain.phrase.Phrase;

public class PhraseCustomRepositoryImpl extends QuerydslRepositorySupport implements PhraseCustomRepository {
	public PhraseCustomRepositoryImpl() {
		super(Phrase.class);
	}
}
