package com.untact.persistent.attendance;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

import com.untact.domain.attendance.Attendance;

public interface AttendanceCustomRepository {
	public Attendance findAttendanceNumberByGroupNumberAndMemberNumberAndBetweenStartTimeAndCurrentTime(Long gno,Long mno,LocalDateTime startTime);
}
