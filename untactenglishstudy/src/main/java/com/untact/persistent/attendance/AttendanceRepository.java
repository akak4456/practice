package com.untact.persistent.attendance;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.untact.domain.attendance.Attendance;
import com.untact.domain.attendance.AttendanceStatus;

public interface AttendanceRepository extends JpaRepository<Attendance, Long>, AttendanceCustomRepository {
	@Modifying
	@Transactional
	@Query("UPDATE Attendance a SET a.status = :status WHERE a.ano=:ano")
	public int updateStatusByAttendanceNumber(@Param("status")AttendanceStatus status,@Param("ano")Long ano);
}
