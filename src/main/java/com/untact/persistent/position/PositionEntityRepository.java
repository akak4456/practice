package com.untact.persistent.position;

import org.springframework.data.jpa.repository.JpaRepository;

import com.untact.domain.position.PositionEntity;

public interface PositionEntityRepository extends JpaRepository<PositionEntity, Long>, PositionEntityCustomRepository {

}
