package com.untact.persistent.antonym;

import org.springframework.data.jpa.repository.JpaRepository;

import com.untact.domain.antonym.Antonym;

public interface AntonymRepository extends JpaRepository<Antonym, Long>, AntonymCustomRepository {

}
