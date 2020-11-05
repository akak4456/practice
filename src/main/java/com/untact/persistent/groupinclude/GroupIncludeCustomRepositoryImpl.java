package com.untact.persistent.groupinclude;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import com.untact.domain.group.GroupEntity;
import com.untact.domain.groupinclude.GroupInclude;
import com.untact.domain.groupinclude.QGroupInclude;
import com.untact.domain.member.MemberEntity;
import com.untact.vo.GroupAndMemberVO;

public class GroupIncludeCustomRepositoryImpl extends QuerydslRepositorySupport
		implements GroupIncludeCustomRepository {
	public GroupIncludeCustomRepositoryImpl() {
		super(GroupInclude.class);
	}

	@Override
	public List<GroupAndMemberVO> findMemberByGroupNumber(List<Long> gno) {
		QGroupInclude groupInclude = QGroupInclude.groupInclude;
		JPQLQuery<Tuple> query = from(groupInclude).select(groupInclude.group,groupInclude.member);
		query.where(groupInclude.group.gno.in(gno));
		List<Tuple> tuplelist = query.fetch();
		List<GroupAndMemberVO> list = new ArrayList<>();
		for(Tuple tuple:tuplelist) {
			GroupEntity group = tuple.get(groupInclude.group);
			MemberEntity member = tuple.get(groupInclude.member);
			list.add(new GroupAndMemberVO(group,member));
		}
		return list;
	}
}
