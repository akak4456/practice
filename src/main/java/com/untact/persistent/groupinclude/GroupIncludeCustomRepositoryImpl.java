package com.untact.persistent.groupinclude;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.untact.domain.groupinclude.GroupInclude;

public class GroupIncludeCustomRepositoryImpl extends QuerydslRepositorySupport
		implements GroupIncludeCustomRepository {
	public GroupIncludeCustomRepositoryImpl() {
		super(GroupInclude.class);
	}
}
