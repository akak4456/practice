package com.untact.service.group;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.untact.domain.group.GroupEntity;
import com.untact.domain.member.MemberEntity;
import com.untact.domain.position.PositionEntity;
import com.untact.domain.position.WhichPosition;
import com.untact.persistent.group.GroupEntityRepository;
import com.untact.persistent.position.PositionEntityRepository;
import com.untact.vo.PageVO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Service
@NoArgsConstructor
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {
	@Autowired
	private GroupEntityRepository groupRepo;
	@Autowired
	private PositionEntityRepository positionRepo;
	
	@Transactional
	@Override
	public Page<GroupEntity> getListWithPaging(PageVO pageVO) {
		return groupRepo.getPage(pageVO.makePageable(0, "gno"));
	}
	
	@Transactional
	@Override
	public void addGroup(GroupEntity group,MemberEntity member) {
		// group add를 신청한 member는 자동으로 group leader가 된다.
		groupRepo.save(group);
		positionRepo.save(new PositionEntity().builder().group(group).member(member).whichPosition(WhichPosition.Leader).build());
	}

	@Transactional
	@Override
	public Page<GroupEntity> getListWithPagingAndUserNumber(PageVO pageVO, Long mno) {
		return groupRepo.getPageWithUserNumber(pageVO.makePageable(0, "gno"), mno);
	}

	@Transactional
	@Override
	public boolean dismissGroupManual(Long gno, MemberEntity member) {
		GroupEntity group = groupRepo.findById(gno).get();
		Optional<PositionEntity> position = positionRepo.findByGroupAndMemberAndWhichPosition(group,member,WhichPosition.Leader);
		if(position.isEmpty()) {
			//리더가 아니라면
			return false;
		}
		//나중에 dismiss할 코드를 추가할 것
		return true;
	}
	
	@Transactional
	@Override
	public void dismissGroupAuto() {
		
	}

}
