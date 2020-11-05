package com.untact.persistent.representativetimetableitem;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.untact.demo.UntactenglishstudyApplication;
import com.untact.domain.group.GroupEntity;
import com.untact.domain.representativetimetable.RepresentativeTimeTable;
import com.untact.domain.representativetimetableitem.RepresentativeTimeTableItem;
import com.untact.persistent.group.GroupEntityRepository;
import com.untact.persistent.representativetimetable.RepresentativeTimeTableRepository;
import com.untact.persistent.util.DeleteAllUtil;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UntactenglishstudyApplication.class)
@Transactional
@Commit
@Log
public class RepresentativeTimeTableItemTest {
	@Autowired
	private DeleteAllUtil deleteAllUtil;
	
	@Autowired
	private RepresentativeTimeTableRepository representativeTimeTableRepo;
	
	@Autowired
	private RepresentativeTimeTableItemRepository representativeTimeTableItemRepo;
	
	@Autowired
	private GroupEntityRepository groupRepo;
	
	@Before
	public void setUp() {
		deleteAllUtil.deleteAllRepo();
	}
	
	@Test
	public void initTest() {
		GroupEntity group = GroupEntity.builder().title("title").build();
		groupRepo.save(group);
		RepresentativeTimeTable table1 = RepresentativeTimeTable.builder().title("title1").build();
		representativeTimeTableRepo.save(table1);
		List<RepresentativeTimeTableItem> list1 = new ArrayList<>();
		list1.add(RepresentativeTimeTableItem.builder()
				.title("title1")
				.detail("detail1")
				.day(0)
				.startHour(10)
				.startMinute(0)
				.endHour(12)
				.endMinute(0)
				.group(group)
				.representativeTimeTable(table1).build());
		list1.add(RepresentativeTimeTableItem.builder()
				.title("title2")
				.detail("detail2")
				.day(0)
				.startHour(11)
				.startMinute(0)
				.endHour(13)
				.endMinute(0)
				.group(group)
				.representativeTimeTable(table1).build());
		list1.add(RepresentativeTimeTableItem.builder()
				.title("title3")
				.detail("detail3")
				.day(0)
				.startHour(13)
				.startMinute(0)
				.endHour(15)
				.endMinute(0)
				.group(group)
				.representativeTimeTable(table1).build());
		list1.add(RepresentativeTimeTableItem.builder()
				.title("title4")
				.detail("detail4")
				.day(1)
				.startHour(10)
				.startMinute(0)
				.endHour(12)
				.endMinute(0)
				.group(group)
				.representativeTimeTable(table1).build());
		list1.add(RepresentativeTimeTableItem.builder()
				.title("title5")
				.detail("detail5")
				.day(1)
				.startHour(11)
				.startMinute(0)
				.endHour(13)
				.endMinute(0)
				.group(group)
				.representativeTimeTable(table1).build());
		list1.add(RepresentativeTimeTableItem.builder()
				.title("title6")
				.detail("detail6")
				.day(1)
				.startHour(13)
				.startMinute(0)
				.endHour(15)
				.endMinute(0)
				.group(group)
				.representativeTimeTable(table1).build());
		representativeTimeTableItemRepo.saveAll(list1);
		RepresentativeTimeTable table2 = RepresentativeTimeTable.builder().title("title2").build();
		representativeTimeTableRepo.save(table2);
		List<RepresentativeTimeTableItem> list2 = new ArrayList<>();
		list2.add(RepresentativeTimeTableItem.builder()
				.title("title1")
				.detail("detail1")
				.day(0)
				.startHour(10)
				.startMinute(0)
				.endHour(12)
				.endMinute(0)
				.group(group)
				.representativeTimeTable(table2).build());
		list2.add(RepresentativeTimeTableItem.builder()
				.title("title2")
				.detail("detail2")
				.day(0)
				.startHour(11)
				.startMinute(0)
				.endHour(13)
				.endMinute(0)
				.group(group)
				.representativeTimeTable(table2).build());
		list2.add(RepresentativeTimeTableItem.builder()
				.title("title3")
				.detail("detail3")
				.day(0)
				.startHour(13)
				.startMinute(0)
				.endHour(15)
				.endMinute(0)
				.group(group)
				.representativeTimeTable(table2).build());
		list2.add(RepresentativeTimeTableItem.builder()
				.title("title4")
				.detail("detail4")
				.day(1)
				.startHour(10)
				.startMinute(0)
				.endHour(12)
				.endMinute(0)
				.group(group)
				.representativeTimeTable(table2).build());
		list2.add(RepresentativeTimeTableItem.builder()
				.title("title5")
				.detail("detail5")
				.day(1)
				.startHour(11)
				.startMinute(0)
				.endHour(13)
				.endMinute(0)
				.group(group)
				.representativeTimeTable(table2).build());
		list2.add(RepresentativeTimeTableItem.builder()
				.title("title6")
				.detail("detail6")
				.day(1)
				.startHour(13)
				.startMinute(0)
				.endHour(15)
				.endMinute(0)
				.group(group)
				.representativeTimeTable(table2).build());
		representativeTimeTableItemRepo.saveAll(list2);
		assertEquals(representativeTimeTableItemRepo.findGroupNumberByDayAndStartTime(0, 10, 0).size(),2);
		assertEquals(representativeTimeTableItemRepo.findGroupNumberByDayAndStartTime(0, 10, 1).size(),0);
		assertEquals(representativeTimeTableItemRepo.findGroupNumberByDayAndStartTime(0, 11, 0).size(),2);
		assertEquals(representativeTimeTableItemRepo.findGroupNumberByDayAndStartTime(0, 11, 1).size(),0);
		assertEquals(representativeTimeTableItemRepo.findGroupNumberByDayAndStartTime(0, 13, 0).size(),2);
		assertEquals(representativeTimeTableItemRepo.findGroupNumberByDayAndStartTime(0, 13, 1).size(),0);
		assertEquals(representativeTimeTableItemRepo.findGroupNumberByDayAndStartTime(1, 10, 0).size(),2);
		assertEquals(representativeTimeTableItemRepo.findGroupNumberByDayAndStartTime(1, 10, 1).size(),0);
		assertEquals(representativeTimeTableItemRepo.findGroupNumberByDayAndStartTime(1, 11, 0).size(),2);
		assertEquals(representativeTimeTableItemRepo.findGroupNumberByDayAndStartTime(1, 11, 1).size(),0);
		assertEquals(representativeTimeTableItemRepo.findGroupNumberByDayAndStartTime(1, 13, 0).size(),2);
		assertEquals(representativeTimeTableItemRepo.findGroupNumberByDayAndStartTime(1, 13, 1).size(),0);
	}
}
