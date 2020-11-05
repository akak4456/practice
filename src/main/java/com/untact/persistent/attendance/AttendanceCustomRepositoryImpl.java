package com.untact.persistent.attendance;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.JPQLQuery;
import com.untact.domain.attendance.Attendance;
import com.untact.domain.attendance.QAttendance;

public class AttendanceCustomRepositoryImpl extends QuerydslRepositorySupport implements AttendanceCustomRepository {
	public AttendanceCustomRepositoryImpl() {
		super(Attendance.class);
	}

	@Override
	public Long findAttendanceNumberByGroupNumberAndMemberNumberAndBetweenStartTimeAndCurrentTime(Long gno,Long mno,LocalDateTime startTime) {
		QAttendance attendance = QAttendance.attendance;
		JPQLQuery<Attendance> query = from(attendance);
		query.where(
				attendance.group.gno.eq(gno)
				.and(attendance.member.mno.eq(mno))
				.and(attendance.regdate.between(startTime,LocalDateTime.now()))
				);
		Attendance a = query.fetchOne();
		if(a == null) {
			return -1L;
		}else {
			return a.getAno();
		}
	}

}
