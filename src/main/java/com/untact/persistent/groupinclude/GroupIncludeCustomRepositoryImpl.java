package com.untact.persistent.groupinclude;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import com.untact.domain.group.QGroupEntity;
import com.untact.domain.groupinclude.GroupInclude;
import com.untact.domain.groupinclude.QGroupInclude;
import com.untact.domain.member.QMemberEntity;
import com.untact.vo.MemberManageVO;

public class GroupIncludeCustomRepositoryImpl extends QuerydslRepositorySupport
		implements GroupIncludeCustomRepository {
	public GroupIncludeCustomRepositoryImpl() {
		super(GroupInclude.class);
	}

	@Override
	public List<GroupInclude> findByGroupNumbers(List<Long> gno) {
		QGroupInclude groupInclude = QGroupInclude.groupInclude;
		QGroupEntity group = QGroupEntity.groupEntity;
		QMemberEntity member = QMemberEntity.memberEntity;
		JPQLQuery<GroupInclude> query = from(groupInclude);
		query.innerJoin(groupInclude.group,group).fetchJoin();
		query.innerJoin(groupInclude.member,member).fetchJoin();
		query.where(groupInclude.group.gno.in(gno));
		return query.fetch();
	}

	@Override
	public List<MemberManageVO> findMemberManageByGroupNumber(Long gno) {
		QGroupInclude groupInclude = QGroupInclude.groupInclude;
		JPQLQuery<Tuple> query = from(groupInclude).select(
													groupInclude.member.name,
													groupInclude.attendance,
													groupInclude.absent,
													groupInclude.late,
													groupInclude.deposit,
													groupInclude.fine,
													groupInclude.reward
													);
		query.where(groupInclude.group.gno.eq(gno));
		List<Tuple> tupleList = query.fetch();
		List<MemberManageVO> list = new ArrayList<>();
		for(Tuple t:tupleList) {
			list.add(MemberManageVO.builder()
									.name(t.get(groupInclude.member.name))
									.attendance(t.get(groupInclude.attendance))
									.absent(t.get(groupInclude.absent))
									.late(t.get(groupInclude.late))
									.deposit(t.get(groupInclude.deposit))
									.fine(t.get(groupInclude.fine))
									.reward(t.get(groupInclude.reward))
									.build()
					);
		}
		return list;
	}
}
