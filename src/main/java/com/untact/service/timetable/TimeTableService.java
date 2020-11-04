package com.untact.service.timetable;

import java.util.List;

import org.springframework.data.domain.Page;

import com.untact.domain.member.MemberEntity;
import com.untact.domain.representativetimetableitem.RepresentativeTimeTableItem;
import com.untact.domain.timetable.TimeTable;
import com.untact.domain.timetableitem.TimeTableItem;
import com.untact.exception.NotGroupLeaderException;
import com.untact.exception.NotIncludeGroupException;
import com.untact.exception.TimeTableNotCorrectException;
import com.untact.vo.PageVO;
import com.untact.vo.RepresentativeTimeTableVO;
import com.untact.vo.TimeTableVO;

public interface TimeTableService {
	public Page<TimeTable> getListWithPagingAndGroupNumberAndMemberNumber(PageVO pageVO,Long gno, Long mno);
	public TimeTableVO getOne(Long tno);
	public void addTimeTable(Long gno,MemberEntity member,TimeTable timeTable,List<TimeTableItem> timeTableItem) throws NotIncludeGroupException,TimeTableNotCorrectException;
	public void modifyTimeTable(Long gno,Long mno,Long tno,TimeTable targetTimeTable,List<TimeTableItem> targetTimeTableItem) throws NotIncludeGroupException,TimeTableNotCorrectException;
	public void deleteTimeTable(Long gno,Long mno,Long tno) throws NotIncludeGroupException;
	
	public RepresentativeTimeTableVO getRepresentativeOne(Long gno,MemberEntity member) throws NotGroupLeaderException;
	public void modifyRepresentativeTimeTable(Long gno, MemberEntity member, String title,List<RepresentativeTimeTableItem> targetTimeTableItem) throws NotGroupLeaderException, TimeTableNotCorrectException;
}
