package com.untact.persistent.score;

import org.springframework.data.jpa.repository.JpaRepository;

import com.untact.domain.score.Score;

public interface ScoreRepository extends JpaRepository<Score, Long>, ScoreCustomRepository {

}
