package com.untact.persistent.grouprule;

import org.springframework.data.jpa.repository.JpaRepository;

import com.untact.domain.grouprule.GroupRule;

public interface GroupRuleRepository extends JpaRepository<GroupRule, Long>, GroupRuleCustomRepository {

}
