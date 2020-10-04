package com.untact.persistent.groupruleauto;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.untact.domain.groupruleauto.GroupRuleAuto;

public class GroupRuleAutoCustomRepositoryImpl extends QuerydslRepositorySupport implements GroupRuleAutoCustomRepository {
	public GroupRuleAutoCustomRepositoryImpl() {
		super(GroupRuleAuto.class);
	}
}
