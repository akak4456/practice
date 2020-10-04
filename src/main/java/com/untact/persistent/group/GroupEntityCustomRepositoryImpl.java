package com.untact.persistent.group;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.untact.domain.group.GroupEntity;

public class GroupEntityCustomRepositoryImpl extends QuerydslRepositorySupport implements GroupEntityCustomRepository {
	public GroupEntityCustomRepositoryImpl() {
		super(GroupEntity.class);
	}
}
