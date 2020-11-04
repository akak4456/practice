package com.untact.persistent.attendance;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.untact.domain.attendance.Attendance;

public interface AttendanceCustomRepository {
	public Attendance findAttendanceNumberByGroupNumberAndMemberNumberAndBetweenStartTimeAndCurrentTime(Long gno,Long mno,LocalDateTime startTime);
	
	public Page<Attendance> getPageWithGroupNumberAndMemberNumber(Pageable pageable,Long gno,Long mno);
}
