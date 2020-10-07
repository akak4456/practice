package com.untact.service.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.untact.domain.group.GroupEntity;
import com.untact.persistent.group.GroupEntityRepository;
import com.untact.vo.PageVO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Service
@NoArgsConstructor
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {
	@Autowired
	private GroupEntityRepository repo;
	

	@Override
	public Page<GroupEntity> getListWithPaging(PageVO pageVO) {
		// TODO Auto-generated method stub
		return repo.getPage(pageVO.makePageable(0, "gno"));
	}

	@Override
	public void addGroup(GroupEntity group) {
		// TODO Auto-generated method stub
		repo.save(group);
	}

}
