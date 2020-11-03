package com.untact.persistent.representativetimetableitem;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.untact.domain.representativetimetableitem.RepresentativeTimeTableItem;

public class RepresentativeTimeTableItemCustomRepositoryImpl extends QuerydslRepositorySupport implements RepresentativeTimeTableItemCustomRepository {
	public RepresentativeTimeTableItemCustomRepositoryImpl() {
		super(RepresentativeTimeTableItem.class);
	}
}
