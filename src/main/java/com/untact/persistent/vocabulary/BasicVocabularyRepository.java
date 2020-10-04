package com.untact.persistent.vocabulary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.untact.domain.vocabulary.BasicVocabulary;

public interface BasicVocabularyRepository
		extends JpaRepository<BasicVocabulary, Long>, BasicVocabularyCustomRepository {

}
