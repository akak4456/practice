package com.untact.persistent.groupinclude;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.JPQLQuery;
import com.untact.domain.group.GroupEntity;
import com.untact.domain.groupinclude.GroupInclude;
import com.untact.domain.groupinclude.QGroupInclude;
import com.untact.domain.member.MemberEntity;

public class GroupIncludeCustomRepositoryImpl extends QuerydslRepositorySupport
		implements GroupIncludeCustomRepository {
	public GroupIncludeCustomRepositoryImpl() {
		super(GroupInclude.class);
	}

	@Override
	public List<MemberEntity> findMemberByGroupNumber(Long gno) {
		QGroupInclude groupInclude = QGroupInclude.groupInclude;
		JPQLQuery<MemberEntity> query = from(groupInclude).select(groupInclude.member);
		query.where(groupInclude.group.gno.eq(gno));
		return query.fetch();
	}
}
