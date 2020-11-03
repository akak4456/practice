package com.untact.persistent.representativetimetable;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.untact.domain.representativetimetable.RepresentativeTimeTable;

public class RepresentativeTimeTableCustomRepositoryImpl extends QuerydslRepositorySupport implements RepresentativeTimeTableCustomRepository {
	public RepresentativeTimeTableCustomRepositoryImpl() {
		super(RepresentativeTimeTable.class);
	}
}
