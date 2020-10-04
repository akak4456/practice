package com.untact.persistent.grouprule;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.untact.domain.grouprule.GroupRule;

public class GroupRuleCustomRepositoryImpl extends QuerydslRepositorySupport implements GroupRuleCustomRepository {
	public GroupRuleCustomRepositoryImpl() {
		super(GroupRule.class);
	}
}
