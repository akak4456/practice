package com.untact.service.group;

import org.springframework.data.domain.Page;

import com.untact.domain.group.GroupEntity;
import com.untact.domain.member.MemberEntity;
import com.untact.vo.GroupInfoVO;
import com.untact.vo.PageVO;

public interface GroupService {
	public Page<GroupEntity> getListWithPaging(PageVO pageVO);
	public Page<GroupEntity> getListWithPagingAndUserNumber(PageVO pageVO,Long mno);
	public GroupInfoVO getOne(Long gno);
	public String tryEntrance(Long gno,MemberEntity member);
	public void addGroup(GroupEntity group,MemberEntity member);
	public boolean dismissGroupManual(Long gno,MemberEntity member);
	public void dismissGroupAuto();//cron에 의해 주기적으로 실행되는 함수
	public boolean tryLeaderEntrance(Long gno,MemberEntity member);
	public boolean modifyGroup(Long gno,MemberEntity member,GroupEntity newGroup);
}
