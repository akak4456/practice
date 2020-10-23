package com.untact.persistent.fine;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.untact.domain.fine.Fine;

public interface FineRepository extends JpaRepository<Fine, Long>, FineCustomRepository {
	@Query("select fine from Fine fine where fine.group.gno=:gno and fine.member.mno=:mno")
	public Optional<Fine> findByGroupNumberAndMemberNumber(@Param("gno")Long gno,@Param("mno")Long mno);
}
