package com.untact.persistent.group;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.untact.domain.group.GroupEntity;
import com.untact.domain.representativetimetable.RepresentativeTimeTable;

public interface GroupEntityRepository extends JpaRepository<GroupEntity, Long>, GroupEntityCustomRepository {
}
