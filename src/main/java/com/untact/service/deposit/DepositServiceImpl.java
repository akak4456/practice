package com.untact.service.deposit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.untact.domain.deposit.Deposit;
import com.untact.domain.groupinclude.WhichStatus;
import com.untact.domain.member.MemberEntity;
import com.untact.persistent.deposit.DepositRepository;
import com.untact.persistent.group.GroupEntityRepository;
import com.untact.persistent.groupinclude.GroupIncludeRepository;
import com.untact.vo.AmountVO;

@Service
public class DepositServiceImpl implements DepositService {
	@Autowired
	private GroupEntityRepository groupRepo;
	
	@Autowired
	private DepositRepository depositRepo;
	
	@Autowired
	private GroupIncludeRepository groupIncludeRepo;
	
	@Transactional
	@Override
	public Deposit getDepositWithGroupNumberAndMemberNumber(Long gno, Long mno) {
		return depositRepo.findByGroupNumberAndMemberNumber(gno, mno).get();
	}

	@Transactional
	@Override
	public void addDeposit(Long gno, MemberEntity member, AmountVO amountVO) {
		depositRepo.save(Deposit.builder()
							.group(groupRepo.findById(gno).get())
							.member(member)
							.depositAmount(amountVO.getAmount())
							.build()); 
	}

	@Override
	public boolean changeDeposit(Long oldDepositId,Long gno ,MemberEntity member, AmountVO amountVO) {
		if(groupIncludeRepo.findByGroupNumberAndMemberNumberAndWhichStatus(gno, member.getMno(), WhichStatus.LEADER).isEmpty()) {
			//예치금 수정을 요청한 자가 스터디장이 아니면 거절한다
			return false;
		}
		Deposit oldDeposit = depositRepo.findById(oldDepositId).get();
		Deposit newDeposit = oldDeposit.changeDepositAmount(amountVO.getAmount());
		depositRepo.save(newDeposit);		
		return true;//승낙한다
	}

}
