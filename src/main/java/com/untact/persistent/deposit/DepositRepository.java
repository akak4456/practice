package com.untact.persistent.deposit;

import org.springframework.data.jpa.repository.JpaRepository;

import com.untact.domain.deposit.Deposit;

public interface DepositRepository extends JpaRepository<Deposit, Long>, DepositCustomRepository {

}
