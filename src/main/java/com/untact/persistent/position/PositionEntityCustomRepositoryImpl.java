package com.untact.persistent.position;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.untact.domain.position.PositionEntity;

public class PositionEntityCustomRepositoryImpl extends QuerydslRepositorySupport implements PositionEntityCustomRepository {
	public PositionEntityCustomRepositoryImpl() {
		super(PositionEntity.class);
	}
}
