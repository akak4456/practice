package com.untact.service.attendance;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.untact.controller.TryAttendanceResult;
import com.untact.domain.attendance.Attendance;
import com.untact.domain.attendance.AttendanceStatus;
import com.untact.domain.group.GroupEntity;
import com.untact.domain.groupinclude.GroupInclude;
import com.untact.persistent.attendance.AttendanceRepository;
import com.untact.persistent.group.GroupEntityRepository;
import com.untact.persistent.groupinclude.GroupIncludeRepository;
import com.untact.persistent.representativetimetableitem.RepresentativeTimeTableItemRepository;
import com.untact.vo.PageVO;

import lombok.extern.java.Log;

@Service
@Log
public class AttendanceServiceImpl implements AttendanceService {
	@Autowired
	private GroupEntityRepository groupRepo;
	@Autowired
	private RepresentativeTimeTableItemRepository representativeTimeTableRepo;
	@Autowired
	private GroupIncludeRepository groupIncludeRepo;
	@Autowired
	private AttendanceRepository attendanceRepo;
	private static final long beforeLateTimeAmount = 30;//단위는 분, 이 시간 전까지는 지각이 아니다. 그러나 이 시간 이후로는 지각이다
	private static final long beforeAbsentTimeAmount = 60;//단이는 분, 이 시간 전까지는 결석이 아니다. 그러나 이 시간 이후로든 결석이다
	@Override
	@Transactional
	public void insertAbsentPeriodically(int day, int startHour, int startMinute) {
		List<Long> ids = representativeTimeTableRepo.findGroupNumberByDayAndStartTime(day, startHour, startMinute);
		
		
		List<GroupInclude> groupIncludes = groupIncludeRepo.findByGroupNumbers(ids);
		List<Attendance> attendanceList = groupIncludes.stream()
												.map(
														vo->Attendance.builder()
																.status(AttendanceStatus.ABSENT)
																.group(vo.getGroup())
																.member(vo.getMember())
																.build())
												.collect(Collectors.toList());
		attendanceRepo.saveAll(attendanceList);
		//우선 결석 벌금을 부과 나중에 출석을 하면 벌금이 삭감되는 식
		for(GroupInclude include:groupIncludes) {
			include.addFine(include.getGroup().getFineForBeingAbsence());
			include.incrementAbsent();
		}
		groupIncludeRepo.saveAll(groupIncludes);
	}
	@Transactional
	@Override
	public TryAttendanceResult attendanceCheck(Long gno, Long mno) {
		GroupEntity group = groupRepo.findById(gno).get();
		//시간표 아이템 하나의 시간 길이는 최소 1시간이 되어야 함
		LocalDateTime beforeLateTime = LocalDateTime.now().minusMinutes(beforeLateTimeAmount);
		Attendance beforeLateAttendance = attendanceRepo.findAttendanceNumberByGroupNumberAndMemberNumberAndBetweenStartTimeAndCurrentTime(gno, mno, beforeLateTime);
		if(beforeLateAttendance != null) {
			//지각 시간
			if(beforeLateAttendance.getStatus() == AttendanceStatus.ABSENT) {
				attendanceRepo.updateStatusByAttendanceNumber(AttendanceStatus.OK, beforeLateAttendance.getAno());
				GroupInclude old = groupIncludeRepo.findByGroupNumberAndMemberNumber(gno, mno).get();
				old.subFine(group.getFineForBeingAbsence());
				old.changeAbsentToAttendance();
				groupIncludeRepo.save(old);
				return TryAttendanceResult.attendance;
			}
		}
		LocalDateTime beforeAbsentTime = LocalDateTime.now().minusMinutes(beforeAbsentTimeAmount);
		Attendance beforeAbsentAttendance = attendanceRepo.findAttendanceNumberByGroupNumberAndMemberNumberAndBetweenStartTimeAndCurrentTime(gno, mno, beforeAbsentTime);
		
		if(beforeAbsentAttendance != null) {
			if(beforeAbsentAttendance.getStatus() == AttendanceStatus.ABSENT) {
				attendanceRepo.updateStatusByAttendanceNumber(AttendanceStatus.LATE, beforeAbsentAttendance.getAno());
				GroupInclude old = groupIncludeRepo.findByGroupNumberAndMemberNumber(gno, mno).get();
				old.subFine(group.getFineForBeingAbsence()-group.getFineForBeingLate());
				old.changeAbsentToLate();
				groupIncludeRepo.save(old);
				return TryAttendanceResult.late;
			}
		}
		return TryAttendanceResult.notaccept;
	}
	@Override
	public Long getRemainTime(Long gno, Long mno) {
		LocalDateTime beforeAbsentTime = LocalDateTime.now().minusMinutes(beforeAbsentTimeAmount);
		Attendance beforeAbsentAttendance = attendanceRepo.findAttendanceNumberByGroupNumberAndMemberNumberAndBetweenStartTimeAndCurrentTime(gno, mno, beforeAbsentTime);
		if(beforeAbsentAttendance == null) {
			return 0L;
		}else {
			LocalDateTime endTime = beforeAbsentAttendance.getRegdate();
			return ChronoUnit.MINUTES.between(LocalDateTime.now(), endTime.plusMinutes(beforeAbsentTimeAmount));
		}
	}
	@Override
	public Page<Attendance> getList(PageVO pageVO, Long gno, Long mno) {
		return attendanceRepo.getPageWithGroupNumberAndMemberNumber(pageVO.makePageable(0, "ano"), gno, mno);
	}

}
