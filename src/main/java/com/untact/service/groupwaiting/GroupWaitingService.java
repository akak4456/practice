package com.untact.service.groupwaiting;

import com.untact.vo.GroupWaitingVO;

public interface GroupWaitingService {
	public void requestJoin(GroupWaitingVO groupWaitingVO);
	public void acceptJoin(Long gwno);
	public void rejectJoin(Long gwno);
}
