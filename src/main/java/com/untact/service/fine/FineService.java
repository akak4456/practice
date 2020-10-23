package com.untact.service.fine;

import com.untact.domain.fine.Fine;
import com.untact.domain.member.MemberEntity;
import com.untact.vo.AmountVO;

public interface FineService {
	public Fine getFineWithGroupNumberAndMemberNumber(Long gno,Long mno);
	public void addFine (Long gno,MemberEntity member,AmountVO amountVO);
	public boolean changeFine (Long oldFineId,Long gno,MemberEntity member,AmountVO amountVO);
}
