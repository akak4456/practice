package com.untact.persistent.attendance;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.untact.domain.attendance.Attendance;
import com.untact.vo.AttendanceVO;

public interface AttendanceCustomRepository {
	public Attendance findAttendanceNumberByGroupNumberAndMemberNumberAndBetweenStartTimeAndCurrentTime(Long gno,Long mno,LocalDateTime startTime);
	
	public Page<Attendance> getPageWithGroupNumberAndMemberNumber(Pageable pageable,Long gno,Long mno);
	
	public List<AttendanceVO> getAttendanceResponseWithGroupNumberAndLocalDate(Long gno,LocalDate time);
}
