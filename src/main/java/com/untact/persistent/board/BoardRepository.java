package com.untact.persistent.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.untact.domain.board.Board;
import com.untact.domain.file.FileEntity;

public interface BoardRepository extends JpaRepository<Board, Long>,BoardCustomRepository {
}
