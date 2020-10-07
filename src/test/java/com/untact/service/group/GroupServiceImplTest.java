package com.untact.service.group;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.untact.domain.group.GroupEntity;
import com.untact.persistent.group.GroupEntityRepository;

@RunWith(MockitoJUnitRunner.class)
public class GroupServiceImplTest {
	@Mock
	private GroupEntityRepository groupRepo;
	
	@InjectMocks
	private GroupServiceImpl groupService;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		//groupService = new GroupServiceImpl(groupRepo);
	}
	
	@Test
	public void getListWithPagingTest() {
		
	}
	
	@Test
	public void addGroupTest() {
		GroupEntity group = GroupEntity.builder().title("title1").build();
		groupService.addGroup(group);
		verify(groupRepo,times(1)).save(group);
		GroupEntity group2 = GroupEntity.builder().title("title2").build();
		//groupService.addGroup(group2);
		verify(groupRepo,times(1)).save(group2);
	}
	
}
