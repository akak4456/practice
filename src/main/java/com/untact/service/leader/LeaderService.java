package com.untact.service.leader;

import java.time.LocalDate;
import java.util.List;

import com.untact.domain.member.MemberEntity;
import com.untact.exception.NotGroupLeaderException;
import com.untact.vo.AttendanceVO;
import com.untact.vo.MemberManageVO;

public interface LeaderService {
	public List<MemberManageVO> getListWithGroupNumber(Long gno,MemberEntity member) throws NotGroupLeaderException;
	public boolean forceExit(Long gno,MemberEntity leader,Long targetMno);
	public boolean changeReward(Long gno,MemberEntity leader,Long targetMno,Long newAmount);
	public boolean changeFine(Long gno,MemberEntity leader,Long targetMno,Long newAmount);
	
	public List<AttendanceVO> getAttendanceListWithGroupNumberAndLocalDate(Long gno,LocalDate time);
	public boolean changeAttendance(Long gno,MemberEntity leader,Long targetMno,Long ano,String oldStatus,String newStatus);
}
