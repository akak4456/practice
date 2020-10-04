package com.untact.persistent.vocabulary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.untact.domain.vocabulary.Vocabulary;

public interface VocabularyRepository extends JpaRepository<Vocabulary, Long>, VocabularyCustomRepository {

}
