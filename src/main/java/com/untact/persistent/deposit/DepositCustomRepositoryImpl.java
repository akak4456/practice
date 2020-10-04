package com.untact.persistent.deposit;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.untact.domain.deposit.Deposit;

public class DepositCustomRepositoryImpl extends QuerydslRepositorySupport implements DepositCustomRepository {
	public DepositCustomRepositoryImpl() {
		super(Deposit.class);
	}
}
