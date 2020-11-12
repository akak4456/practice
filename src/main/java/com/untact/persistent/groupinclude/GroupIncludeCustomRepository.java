package com.untact.persistent.groupinclude;

import java.util.List;

import com.untact.domain.groupinclude.GroupInclude;
import com.untact.vo.MemberManageVO;

public interface GroupIncludeCustomRepository {
	public List<GroupInclude> findByGroupNumber(Long gno);
	public List<GroupInclude> findByGroupNumbers(List<Long> gno);
	public List<MemberManageVO> findMemberManageByGroupNumber(Long gno);
} 
