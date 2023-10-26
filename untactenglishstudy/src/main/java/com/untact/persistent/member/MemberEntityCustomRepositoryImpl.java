package com.untact.persistent.member;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.untact.domain.member.MemberEntity;

public class MemberEntityCustomRepositoryImpl extends QuerydslRepositorySupport
		implements MemberEntityCustomRepository {
	public MemberEntityCustomRepositoryImpl() {
		super(MemberEntity.class);
	}
}
