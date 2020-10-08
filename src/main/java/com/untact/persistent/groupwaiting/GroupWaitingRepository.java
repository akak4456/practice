package com.untact.persistent.groupwaiting;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.untact.domain.groupwaiting.GroupWaiting;
import com.untact.domain.groupwaiting.GroupWaitingStatus;

public interface GroupWaitingRepository extends GroupWaitingCustomRepository, JpaRepository<GroupWaiting, Long> {
	@Modifying
	@Transactional
	@Query("update GroupWaiting g set g.status = :status WHERE g.gwno = :gwno")
	public int changeStatus(@Param("status")GroupWaitingStatus status,@Param("gwno") Long gwno);
}
