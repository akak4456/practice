package com.untact.persistent.score;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.untact.domain.score.Score;

public class ScoreCustomRepositoryImpl extends QuerydslRepositorySupport implements ScoreCustomRepository {
	public ScoreCustomRepositoryImpl() {
		super(Score.class);
	}
}
