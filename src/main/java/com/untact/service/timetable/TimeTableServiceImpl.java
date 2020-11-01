package com.untact.service.timetable;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.untact.domain.groupinclude.GroupInclude;
import com.untact.domain.groupinclude.WhichStatus;
import com.untact.domain.member.MemberEntity;
import com.untact.domain.timetable.TimeTable;
import com.untact.domain.timetableitem.Time;
import com.untact.domain.timetableitem.TimeTableItem;
import com.untact.exception.NotIncludeGroupException;
import com.untact.exception.TimeTableNotCorrectException;
import com.untact.persistent.group.GroupEntityRepository;
import com.untact.persistent.groupinclude.GroupIncludeRepository;
import com.untact.persistent.timetable.TimeTableRepository;
import com.untact.persistent.timetableitem.TimeTableItemRepository;
import com.untact.vo.PageVO;
import com.untact.vo.TimeTableVO;

@Service
public class TimeTableServiceImpl implements TimeTableService {
	/*
    시간표가 맞는지, 겹치는 스티커들(일정)은 없는지 확인
     */
	@Autowired
	private TimeTableRepository timeTableRepo;
	@Autowired
	private TimeTableItemRepository timeTableItemRepo;
	@Autowired
	private GroupEntityRepository groupRepo;
	@Autowired
	private GroupIncludeRepository groupIncludeRepo;
	@Override
	@Transactional
	public Page<TimeTable> getListWithPagingAndGroupNumberAndMemberNumber(PageVO pageVO, Long gno, Long mno) {
		return timeTableRepo.getPageWithGroupNumberAndMemberNumber(pageVO.makePageable(0, "tno"), gno, mno);
	}
	@Override
	@Transactional
	public TimeTableVO getOne(Long tno) {
		return new TimeTableVO(timeTableRepo.findById(tno).get(),timeTableItemRepo.findByTimeTableNumber(tno));
	}
	@Override
	@Transactional
	public void addTimeTable(Long gno,MemberEntity member,TimeTable timeTable, List<TimeTableItem> timeTableItem) throws NotIncludeGroupException,TimeTableNotCorrectException {
		groupIncludeCheck(gno,member.getMno());
		if(!verifyTimeTableItems(timeTableItem)) {
			throw new TimeTableNotCorrectException();
		}
		timeTable.setGroup(groupRepo.findById(gno).get());
		timeTable.setMember(member);
		timeTableRepo.save(timeTable);
		for(TimeTableItem item:timeTableItem) {
			item.setTimeTable(timeTable);
		}
		timeTableItemRepo.saveAll(timeTableItem);
	}
	@Override
	@Transactional
	public void modifyTimeTable(Long gno,Long mno,Long tno, TimeTable targetTable, List<TimeTableItem> targetTimeTableItem)
			throws NotIncludeGroupException,TimeTableNotCorrectException {
		groupIncludeCheck(gno,mno);
		if(!verifyTimeTableItems(targetTimeTableItem)) {
			throw new TimeTableNotCorrectException();
		}
		TimeTable oldTimeTable = timeTableRepo.findById(tno).get();
		TimeTable newTimeTable = oldTimeTable.modifyThisToTargetTimeTable(targetTable);
		timeTableRepo.save(newTimeTable);
		timeTableItemRepo.deleteByTimeTableNumber(tno);
		for(TimeTableItem item:targetTimeTableItem) {
			item.setTimeTable(newTimeTable);
		}
		timeTableItemRepo.saveAll(targetTimeTableItem);
	}
	@Override
	@Transactional
	public void deleteTimeTable(Long gno, Long mno, Long tno) throws NotIncludeGroupException {
		groupIncludeCheck(gno,mno);
		timeTableRepo.deleteById(tno);
	}
	private void groupIncludeCheck(Long gno,Long mno) throws NotIncludeGroupException {
		Optional<GroupInclude> groupIncludeOptional = groupIncludeRepo.findByGroupNumberAndMemberNumber(gno, mno);
		if(groupIncludeOptional.isEmpty()) {
			//가입 시도를 한 적이 없다면
			throw new NotIncludeGroupException();
		}
		GroupInclude groupInclude = groupIncludeOptional.get();
		if(groupInclude.getWhichStatus().equals(WhichStatus.LEADER)||groupInclude.getWhichStatus().equals(WhichStatus.FOLLOWER)) {
			//그룹을 가입한 적이 있다면
			
		}else {
			//그룹을 가입한 적이 없다면
			throw new NotIncludeGroupException();
		}
	}
	
	private boolean verifyTimeTableItems(List<TimeTableItem> timeTableItems) {
		for(int i=0;i<timeTableItems.size();i++) {
			TimeTableItem a = timeTableItems.get(i);
			Time startTime = new Time(a.getStartHour(),a.getStartMinute());
			Time endTime = new Time(a.getEndHour(),a.getEndMinute());
			if(endTime.isLessThan(startTime)) {
				return false;
			}
			for(int j=i+1;j<timeTableItems.size();j++) {
				TimeTableItem b= timeTableItems.get(j);
				if(a.getDay() == b.getDay()&&a.isOverlap(b)) {
					return false;
				}
			}
		}
		return true;
	}
	

}
