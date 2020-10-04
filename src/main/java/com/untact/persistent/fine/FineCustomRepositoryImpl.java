package com.untact.persistent.fine;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.untact.domain.fine.Fine;

public class FineCustomRepositoryImpl extends QuerydslRepositorySupport implements FineCustomRepository {
	public FineCustomRepositoryImpl() {
		super(Fine.class);
	}
}
