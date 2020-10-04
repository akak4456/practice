package com.untact.persistent.groupruleauto;

import org.springframework.data.jpa.repository.JpaRepository;

import com.untact.domain.groupruleauto.GroupRuleAuto;

public interface GroupRuleAutoRepository extends JpaRepository<GroupRuleAuto, Long>, GroupRuleAutoCustomRepository {

}
