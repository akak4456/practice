package com.untact.persistent.groupinclude;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.untact.domain.groupinclude.GroupInclude;

public interface GroupIncludeRepository extends GroupIncludeCustomRepository, JpaRepository<GroupInclude, Long> {
}
