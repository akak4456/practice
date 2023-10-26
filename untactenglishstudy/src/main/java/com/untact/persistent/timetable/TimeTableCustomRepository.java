package com.untact.persistent.timetable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.untact.domain.timetable.TimeTable;

public interface TimeTableCustomRepository {
	public Page<TimeTable> getPageWithGroupNumberAndMemberNumber(Pageable pageable,Long gno,Long mno);
}
