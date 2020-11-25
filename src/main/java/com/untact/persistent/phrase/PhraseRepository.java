package com.untact.persistent.phrase;

import org.springframework.data.jpa.repository.JpaRepository;

import com.untact.domain.phrase.Phrase;

public interface PhraseRepository extends JpaRepository<Phrase, Long>, PhraseCustomRepository {

}
