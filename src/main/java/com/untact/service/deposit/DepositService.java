package com.untact.service.deposit;

import com.untact.domain.deposit.Deposit;
import com.untact.domain.member.MemberEntity;
import com.untact.vo.AmountVO;

public interface DepositService {
	public Deposit getDepositWithGroupNumberAndMemberNumber(Long gno,Long mno);
	public void addDeposit (Long gno,MemberEntity member,AmountVO amountVO);
	public boolean changeDeposit (Long oldDepositId,Long gno,MemberEntity member,AmountVO amountVO);
}
