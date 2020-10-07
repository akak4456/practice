package com.untact.service.group;

import org.springframework.data.domain.Page;

import com.untact.domain.group.GroupEntity;
import com.untact.vo.PageVO;

public interface GroupService {
	public Page<GroupEntity> getListWithPaging(PageVO pageVO);
	public void addGroup(GroupEntity group);
}
