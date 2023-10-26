package com.untact.persistent.antonym;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.untact.domain.antonym.Antonym;

public class AntonymCustomRepositoryImpl extends QuerydslRepositorySupport implements AntonymCustomRepository {
	public AntonymCustomRepositoryImpl() {
		super(Antonym.class);
	}
}
