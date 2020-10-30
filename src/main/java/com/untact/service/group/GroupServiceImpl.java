package com.untact.service.group;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.untact.controller.TryEntranceResult;
import com.untact.domain.group.GroupEntity;
import com.untact.domain.groupinclude.GroupInclude;
import com.untact.domain.groupinclude.WhichStatus;
import com.untact.domain.member.MemberEntity;
import com.untact.persistent.group.GroupEntityRepository;
import com.untact.persistent.groupinclude.GroupIncludeRepository;
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
	private GroupIncludeRepository groupIncludeRepo;
	
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
		groupIncludeRepo.save(new GroupInclude().builder().group(group).member(member).whichStatus(WhichStatus.LEADER).build());
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
		Optional<GroupInclude> include = groupIncludeRepo.findByGroupNumberAndMemberNumberAndWhichStatus(gno, member.getMno(), WhichStatus.LEADER);
		if(include.isEmpty()) {
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

	@Override
	public String tryEntrance(Long gno, MemberEntity member) {
		Optional<GroupInclude> groupIncludeOptional = groupIncludeRepo.findByGroupNumberAndMemberNumber(gno, member.getMno());
		if(groupIncludeOptional.isEmpty()) {
			//가입 시도를 한 적이 없다면
			return TryEntranceResult.first.toString();
		}
		GroupInclude groupInclude = groupIncludeOptional.get();
		if(groupInclude.getWhichStatus().equals(WhichStatus.LEADER)||groupInclude.getWhichStatus().equals(WhichStatus.FOLLOWER)) {
			//그룹을 가입한 적이 있다면
			return TryEntranceResult.success.toString();
		}
		if(groupInclude.getWhichStatus().equals(WhichStatus.WAITING)) {
			return TryEntranceResult.wait.toString();
		}
		return TryEntranceResult.denied.toString();
	}

	@Override
	public GroupEntity getOne(Long gno) {
		return groupRepo.findById(gno).get();
	}

}
