package com.untact.persistent.groupwaiting;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.untact.domain.groupwaiting.GroupWaiting;

public class GroupWaitingCustomRepositoryImpl extends QuerydslRepositorySupport
		implements GroupWaitingCustomRepository {
	public GroupWaitingCustomRepositoryImpl() {
		super(GroupWaiting.class);
	}
}
