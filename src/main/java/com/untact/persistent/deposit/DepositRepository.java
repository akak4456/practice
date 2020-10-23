package com.untact.persistent.deposit;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.untact.domain.deposit.Deposit;

public interface DepositRepository extends JpaRepository<Deposit, Long>, DepositCustomRepository {
	@Query("select deposit from Deposit deposit where deposit.group.gno=:gno and deposit.member.mno=:mno")
	public Optional<Deposit> findByGroupNumberAndMemberNumber(@Param("gno")Long gno,@Param("mno")Long mno);
}
