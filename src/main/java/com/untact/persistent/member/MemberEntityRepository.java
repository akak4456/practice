package com.untact.persistent.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.untact.domain.member.MemberEntity;

public interface MemberEntityRepository extends JpaRepository<MemberEntity, Long>, MemberEntityCustomRepository {
	public Optional<MemberEntity> findByEmail(String email);
}
