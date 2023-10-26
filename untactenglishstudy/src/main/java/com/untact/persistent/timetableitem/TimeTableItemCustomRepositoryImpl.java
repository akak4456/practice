package com.untact.persistent.timetableitem;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.untact.domain.timetableitem.TimeTableItem;

public class TimeTableItemCustomRepositoryImpl extends QuerydslRepositorySupport
		implements TimeTableItemCustomRepository {
	public TimeTableItemCustomRepositoryImpl() {
		super(TimeTableItem.class);
	}
}
