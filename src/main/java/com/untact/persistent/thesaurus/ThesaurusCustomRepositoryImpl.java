package com.untact.persistent.thesaurus;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.untact.domain.thesaurus.Thesaurus;

public class ThesaurusCustomRepositoryImpl extends QuerydslRepositorySupport implements ThesaurusCustomRepository {
	public ThesaurusCustomRepositoryImpl() {
		super(Thesaurus.class);
	}
}
