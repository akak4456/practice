package com.untact.persistent.groupinclude;

import java.util.List;

import com.untact.vo.GroupAndMemberVO;

public interface GroupIncludeCustomRepository {
	public List<GroupAndMemberVO> findMemberByGroupNumber(List<Long> gno);
}
