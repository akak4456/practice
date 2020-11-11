package com.untact.persistent.reward;

import org.springframework.data.jpa.repository.JpaRepository;

import com.untact.domain.reward.Reward;

public interface RewardRepository extends JpaRepository<Reward, Long>, RewardCustomRepository {

}
