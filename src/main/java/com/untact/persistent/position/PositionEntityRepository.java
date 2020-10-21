package com.untact.persistent.position;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.untact.domain.group.GroupEntity;
import com.untact.domain.member.MemberEntity;
import com.untact.domain.position.PositionEntity;
import com.untact.domain.position.WhichPosition;

public interface PositionEntityRepository extends JpaRepository<PositionEntity, Long>, PositionEntityCustomRepository {
	@Query("select position from PositionEntity position where position.group=:group and position.member=:member and position.whichPosition=:whichPosition")
	public Optional<PositionEntity> findByGroupAndMemberAndWhichPosition(@Param("group")GroupEntity group,@Param("member")MemberEntity member,@Param("whichPosition")WhichPosition whichPosition);
}
