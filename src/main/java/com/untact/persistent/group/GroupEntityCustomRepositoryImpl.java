package com.untact.persistent.group;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.JPQLQuery;
import com.untact.domain.group.GroupEntity;
import com.untact.domain.group.QGroupEntity;

public class GroupEntityCustomRepositoryImpl extends QuerydslRepositorySupport implements GroupEntityCustomRepository {
	public GroupEntityCustomRepositoryImpl() {
		super(GroupEntity.class);
	}

	@Override
	public Page<GroupEntity> getPage(Pageable pageable) {
		// TODO Auto-generated method stub
		final QGroupEntity group = QGroupEntity.groupEntity;
		final JPQLQuery<GroupEntity> query = from(group);
		Long totalCount = query.fetchCount();
		List<GroupEntity> list = getQuerydsl().applyPagination(pageable, query).fetch();
		return new PageImpl<GroupEntity>(list,pageable,totalCount);
	}
}
