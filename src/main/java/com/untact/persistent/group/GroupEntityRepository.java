package com.untact.persistent.group;

import org.springframework.data.jpa.repository.JpaRepository;

import com.untact.domain.group.GroupEntity;

public interface GroupEntityRepository extends JpaRepository<GroupEntity, Long>, GroupEntityCustomRepository {

}
