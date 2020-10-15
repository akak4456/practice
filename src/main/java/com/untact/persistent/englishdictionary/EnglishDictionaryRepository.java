package com.untact.persistent.englishdictionary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.untact.domain.englishdictionary.EnglishDictionary;

public interface EnglishDictionaryRepository extends JpaRepository<EnglishDictionary, Long>, EnglishDictionaryCustomRepository {

}
