package com.untact.persistent.group;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.untact.domain.group.GroupEntity;

public interface GroupEntityRepository extends JpaRepository<GroupEntity, Long>, GroupEntityCustomRepository {
	@Query("SELECT g.gno FROM GroupEntity g where g.duedate=:duedate")
	public List<Long> findGroupNumberByDuedate(@Param("duedate")LocalDateTime duedate);
}
