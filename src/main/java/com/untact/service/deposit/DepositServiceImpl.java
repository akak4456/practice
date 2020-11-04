package com.untact.service.deposit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.untact.domain.deposit.Deposit;
import com.untact.domain.group.GroupEntity;
import com.untact.domain.groupinclude.WhichStatus;
import com.untact.domain.member.MemberEntity;
import com.untact.exception.UnableToPayDepositException;
import com.untact.persistent.deposit.DepositRepository;
import com.untact.persistent.group.GroupEntityRepository;
import com.untact.persistent.groupinclude.GroupIncludeRepository;
import com.untact.persistent.member.MemberEntityRepository;
import com.untact.vo.AmountVO;

@Service
public class DepositServiceImpl implements DepositService {
	@Autowired
	private GroupEntityRepository groupRepo;
	
	@Autowired
	private DepositRepository depositRepo;
	
	@Autowired
	private GroupIncludeRepository groupIncludeRepo;
	
	@Autowired
	private MemberEntityRepository memberRepo;
	
	@Transactional
	@Override
	public Deposit getDepositWithGroupNumberAndMemberNumber(Long gno, Long mno) {
		return depositRepo.findByGroupNumberAndMemberNumber(gno, mno).get();
	}

	@Transactional
	@Override
	public void addDeposit(Long gno, MemberEntity member) throws UnableToPayDepositException{
		if(groupIncludeRepo.findByGroupNumberAndMemberNumber(gno, member.getMno()).isEmpty()) {
			//그룹 추방 등 그룹에 속한 적이 없으면
			throw new UnableToPayDepositException();
		}
		if(depositRepo.findByGroupNumberAndMemberNumber(gno, member.getMno()).isPresent()) {
			//이미 예치금을 낸 적이 있다면
			throw new UnableToPayDepositException();
		}
		GroupEntity group = groupRepo.findById(gno).get();
		Long expected = group.getDepositToBePaid();
		if(member.getRemainPoint() >= expected) {
			//남은 돈이 지불해야 하는 예치금 보다 크거나 같으면
			depositRepo.save(Deposit.builder()
					.group(groupRepo.findById(gno).get())
					.member(member)
					.depositAmount(expected)
					.build()); 
			Long newPoint = member.getRemainPoint()-expected;
			MemberEntity newMember = member.updateRemainPoint(newPoint);
			memberRepo.save(newMember);//포인트도 갱신해야함
		}else {
			throw new UnableToPayDepositException();
		}
		
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
