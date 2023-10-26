package com.untact.persistent.group;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.untact.domain.group.GroupEntity;

public interface GroupEntityCustomRepository {
	public Page<GroupEntity> getPage(Pageable pageable);
	
	public Page<GroupEntity> getPageWithUserNumber(Pageable pageable, Long mno);
}
