package com.untact.persistent.attendance;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import com.untact.domain.attendance.Attendance;
import com.untact.domain.attendance.QAttendance;
import com.untact.vo.AttendanceVO;

public class AttendanceCustomRepositoryImpl extends QuerydslRepositorySupport implements AttendanceCustomRepository {
	public AttendanceCustomRepositoryImpl() {
		super(Attendance.class);
	}

	@Override
	public Attendance findAttendanceNumberByGroupNumberAndMemberNumberAndBetweenStartTimeAndCurrentTime(Long gno,Long mno,LocalDateTime startTime) {
		QAttendance attendance = QAttendance.attendance;
		JPQLQuery<Attendance> query = from(attendance);
		query.where(
				attendance.group.gno.eq(gno)
				.and(attendance.member.mno.eq(mno))
				.and(attendance.regdate.between(startTime,LocalDateTime.now()))
				);
		return query.fetchOne();
	}

	@Override
	public Page<Attendance> getPageWithGroupNumberAndMemberNumber(Pageable pageable,Long gno, Long mno) {
		QAttendance attendance = QAttendance.attendance;
		JPQLQuery<Attendance> query = from(attendance);
		query.where(attendance.group.gno.eq(gno).and(attendance.member.mno.eq(mno)));
		Long totalCount = query.fetchCount();
		List<Attendance> list = getQuerydsl().applyPagination(pageable, query).fetch();
		return new PageImpl<Attendance>(list,pageable,totalCount);
	}

	@Override
	public List<AttendanceVO> getAttendanceResponseWithGroupNumberAndLocalDate(Long gno, LocalDate time) {
		QAttendance attendance = QAttendance.attendance;
		JPQLQuery<Tuple> query = from(attendance).select(
										attendance.member.mno,
										attendance.member.name,
										attendance.ano,
										attendance.status
									);
		query.where(attendance.group.gno.eq(gno)
					.and(attendance.regdate.between(time.atStartOfDay(), time.plusDays(1L).atStartOfDay())));
		List<Tuple> tuple = query.fetch();
		List<AttendanceVO> response = new ArrayList<>();
		for(Tuple t:tuple) {
			response.add(new AttendanceVO(t.get(attendance.member.mno),t.get(attendance.member.name),t.get(attendance.status).toString(),t.get(attendance.ano)));
		}
		return response;
	}

}
