package com.untact.persistent.reward;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.untact.domain.reward.Reward;

public class RewardCustomRepositoryImpl extends QuerydslRepositorySupport implements RewardCustomRepository {
	public RewardCustomRepositoryImpl() {
		super(Reward.class);
	}
}
