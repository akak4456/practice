package com.untact.persistent.thesaurus;

import org.springframework.data.jpa.repository.JpaRepository;

import com.untact.domain.thesaurus.Thesaurus;

public interface ThesaurusRepository extends JpaRepository<Thesaurus, Long>, ThesaurusCustomRepository {

}
