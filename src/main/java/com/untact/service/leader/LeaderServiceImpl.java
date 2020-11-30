package com.untact.service.leader;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.untact.domain.attendance.AttendanceStatus;
import com.untact.domain.group.GroupEntity;
import com.untact.domain.groupinclude.GroupInclude;
import com.untact.domain.groupinclude.WhichStatus;
import com.untact.domain.member.MemberEntity;
import com.untact.exception.NotGroupLeaderException;
import com.untact.persistent.attendance.AttendanceRepository;
import com.untact.persistent.group.GroupEntityRepository;
import com.untact.persistent.groupinclude.GroupIncludeRepository;
import com.untact.vo.AttendanceVO;
import com.untact.vo.MemberManageVO;

import lombok.extern.java.Log;

@Service
@Log
@Transactional
public class LeaderServiceImpl implements LeaderService {
	@Autowired
	private GroupEntityRepository groupRepo;
	@Autowired
	private GroupIncludeRepository groupIncludeRepo;
	@Autowired
	private AttendanceRepository attendanceRepo;
	@Override
	public List<MemberManageVO> getListWithGroupNumber(Long gno,MemberEntity member) throws NotGroupLeaderException {
		Optional<GroupInclude> include = groupIncludeRepo.findByGroupNumberAndMemberNumberAndWhichStatus(gno, member.getMno(), WhichStatus.LEADER);
		if(include.isEmpty()) {
			throw new NotGroupLeaderException();
		}
		return groupIncludeRepo.findMemberManageByGroupNumber(gno);
	}
	@Override
	public boolean forceExit(Long gno, MemberEntity leader, Long targetMno) {
		if(groupIncludeRepo.findByGroupNumberAndMemberNumberAndWhichStatus(gno, leader.getMno(), WhichStatus.LEADER).isEmpty()) 
			return false;
		if(leader.getMno() == targetMno) {
			return false;
			//리더는 자기 자신을 퇴장시킬수 없다
		}
		groupIncludeRepo.updateStatusByGroupNumberAndMemberNumber(WhichStatus.EJECT, gno,targetMno);
		return true;
	}
	@Override
	public boolean changeReward(Long gno, MemberEntity leader, Long targetMno, Long newAmount) {
		if(groupIncludeRepo.findByGroupNumberAndMemberNumberAndWhichStatus(gno, leader.getMno(), WhichStatus.LEADER).isEmpty()) 
			return false;
		if(newAmount < 0)
			return false;
		Long totalFine = groupIncludeRepo.findSumOfFineByGroupNumber(gno, Set.of(WhichStatus.LEADER,WhichStatus.FOLLOWER));
		Long totalReward = groupIncludeRepo.findSumOfRewardByGroupNumber(gno, Set.of(WhichStatus.LEADER,WhichStatus.FOLLOWER));
		GroupInclude include = groupIncludeRepo.findByGroupNumberAndMemberNumber(gno, targetMno).get();
		if(newAmount-include.getReward() > totalFine-totalReward) {
			return false;
		}
		include.setReward(newAmount);
		groupIncludeRepo.save(include);
		return true;
	}
	@Override
	public boolean changeFine(Long gno, MemberEntity leader, Long targetMno, Long newAmount) {
		if(groupIncludeRepo.findByGroupNumberAndMemberNumberAndWhichStatus(gno, leader.getMno(), WhichStatus.LEADER).isEmpty()) 
			return false;
		if(newAmount < 0)
			return false;
		Long totalFine = groupIncludeRepo.findSumOfFineByGroupNumber(gno, Set.of(WhichStatus.LEADER,WhichStatus.FOLLOWER));
		Long totalReward = groupIncludeRepo.findSumOfRewardByGroupNumber(gno, Set.of(WhichStatus.LEADER,WhichStatus.FOLLOWER));
		GroupInclude include = groupIncludeRepo.findByGroupNumberAndMemberNumber(gno, targetMno).get();
		if(newAmount > include.getDeposit())
			return false;
		if(include.getFine()-newAmount > totalFine-totalReward) {
			return false;
		}
		include.setFine(newAmount);
		groupIncludeRepo.save(include);
		return true;
	}
	@Override
	public List<AttendanceVO> getAttendanceListWithGroupNumberAndLocalDate(Long gno, LocalDate time) {
		// TODO Auto-generated method stub
		return attendanceRepo.getAttendanceResponseWithGroupNumberAndLocalDate(gno, time);
	}
	@Override
	public boolean changeAttendance(Long gno, MemberEntity leader, Long targetMno,Long ano, String oldStatus, String newStatus) {
		GroupEntity group = groupRepo.findById(gno).get();
		if(groupIncludeRepo.findByGroupNumberAndMemberNumberAndWhichStatus(gno, leader.getMno(), WhichStatus.LEADER).isEmpty()) 
			return false;
		AttendanceStatus oldStat = AttendanceStatus.valueOf(oldStatus);
		AttendanceStatus newStat = AttendanceStatus.valueOf(newStatus);
		GroupInclude include = groupIncludeRepo.findByGroupNumberAndMemberNumber(gno, targetMno).get();
		if(oldStat == AttendanceStatus.OK) {
			if(newStat == AttendanceStatus.LATE) {
				include.changeAttendanceToLate();
				include.addFine(group.getFineForBeingLate());
			}else if(newStat == AttendanceStatus.ABSENT) {
				include.changeAttendanceToAbsent();
				include.addFine(group.getFineForBeingAbsence());
			}
		}else if(oldStat == AttendanceStatus.ABSENT) {
			if(newStat == AttendanceStatus.OK) {
				include.changeAbsentToAttendance();
				include.subFine(group.getFineForBeingAbsence());
			}else if(newStat == AttendanceStatus.LATE) {
				include.changeAbsentToLate();
				include.subFine(group.getFineForBeingAbsence()-group.getFineForBeingLate());
			}
		}else if(oldStat == AttendanceStatus.LATE) {
			if(newStat == AttendanceStatus.OK) {
				include.changeLateToAttendance();
				include.subFine(group.getFineForBeingLate());
			}else if(newStat == AttendanceStatus.ABSENT) {
				include.changeLateToAbsent();
				include.subFine(group.getFineForBeingAbsence()-group.getFineForBeingLate());
			}
		}
		groupIncludeRepo.save(include);
		attendanceRepo.updateStatusByAttendanceNumber(newStat, ano);
		return true;
	}
	@Override
	public List<GroupInclude> getWaitingList(Long gno) {
		return groupIncludeRepo.findByGroupNumberAndWhichStatus(gno, WhichStatus.WAITING);
	}
	@Override
	public boolean rejectAll(Long gno, MemberEntity leader) {
		if(groupIncludeRepo.findByGroupNumberAndMemberNumberAndWhichStatus(gno, leader.getMno(), WhichStatus.LEADER).isEmpty()) 
			return false;
		groupIncludeRepo.rejectAllGroup(gno);
		return true;
	}
}
