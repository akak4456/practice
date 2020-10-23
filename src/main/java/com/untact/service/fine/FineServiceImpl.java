package com.untact.service.fine;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.untact.domain.fine.Fine;
import com.untact.domain.groupinclude.GroupInclude;
import com.untact.domain.groupinclude.WhichStatus;
import com.untact.domain.member.MemberEntity;
import com.untact.persistent.fine.FineRepository;
import com.untact.persistent.group.GroupEntityRepository;
import com.untact.persistent.groupinclude.GroupIncludeRepository;
import com.untact.vo.AmountVO;

@Service
public class FineServiceImpl implements FineService {
	@Autowired
	private FineRepository fineRepo;
	@Autowired
	private GroupEntityRepository groupRepo;
	@Autowired
	private GroupIncludeRepository groupIncludeRepo;
	@Override
	public Fine getFineWithGroupNumberAndMemberNumber(Long gno, Long mno) {
		return fineRepo.findByGroupNumberAndMemberNumber(gno, mno).get();
	}

	@Override
	public void addFine(Long gno, MemberEntity member, AmountVO amountVO) {
		fineRepo.save(Fine.builder()
							.group(groupRepo.findById(gno).get())
							.member(member)
							.fineAmount(amountVO.getAmount())
							.build());
	}

	@Override
	public boolean changeFine(Long oldFineId, Long gno, MemberEntity member, AmountVO amountVO) {
		Optional<GroupInclude> groupInclude = groupIncludeRepo.findByGroupNumberAndMemberNumberAndWhichStatus(gno, member.getMno(), WhichStatus.LEADER);
		if(groupInclude.isEmpty()) {
			//벌금 수정을 요청한 자가 스터디장이 아니면 거절한다
			return false;
		}
		Fine oldFine = fineRepo.findById(oldFineId).get();
		Fine newFine = oldFine.changeFineAmount(amountVO.getAmount());
		fineRepo.save(newFine);
		return true;
	}

}
