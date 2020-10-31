package com.untact.service.timetable;

import java.util.List;

import org.springframework.data.domain.Page;

import com.untact.domain.member.MemberEntity;
import com.untact.domain.timetable.TimeTable;
import com.untact.domain.timetableitem.TimeTableItem;
import com.untact.exception.NotIncludeGroupException;
import com.untact.vo.PageVO;
import com.untact.vo.TimeTableVO;

public interface TimeTableService {
	public Page<TimeTable> getListWithPagingAndGroupNumberAndMemberNumber(PageVO pageVO,Long gno, Long mno);
	public TimeTableVO getOne(Long tno);
	public void addTimeTable(Long gno,MemberEntity member,TimeTable timeTable,List<TimeTableItem> timeTableItem) throws NotIncludeGroupException;
	public void modifyTimeTable(Long gno,Long mno,Long tno,TimeTable targetTimeTable,List<TimeTableItem> targetTimeTableItem) throws NotIncludeGroupException;
	public void deleteTimeTable(Long gno,Long mno,Long tno) throws NotIncludeGroupException;
}
