package com.untact.persistent.board;

import org.springframework.data.jpa.repository.JpaRepository;

import com.untact.domain.board.Board;

public interface BoardRepository extends JpaRepository<Board, Long>,BoardCustomRepository {

}
