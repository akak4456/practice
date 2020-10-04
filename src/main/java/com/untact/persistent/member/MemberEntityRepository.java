package com.untact.persistent.member;

import org.springframework.data.jpa.repository.JpaRepository;

import com.untact.domain.member.MemberEntity;

public interface MemberEntityRepository extends JpaRepository<MemberEntity, Long>, MemberEntityCustomRepository {

}
