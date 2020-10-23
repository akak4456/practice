package com.untact.persistent.deposit;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.JPQLQuery;
import com.untact.domain.board.Board;
import com.untact.domain.board.QBoard;
import com.untact.domain.deposit.Deposit;
import com.untact.domain.deposit.QDeposit;

public class DepositCustomRepositoryImpl extends QuerydslRepositorySupport implements DepositCustomRepository {
	public DepositCustomRepositoryImpl() {
		super(Deposit.class);
	}
}
