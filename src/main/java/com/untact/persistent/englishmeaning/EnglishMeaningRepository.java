package com.untact.persistent.englishmeaning;

import org.springframework.data.jpa.repository.JpaRepository;

import com.untact.domain.englishmeaning.EnglishMeaning;

public interface EnglishMeaningRepository extends JpaRepository<EnglishMeaning, Long>, EnglishMeaningCustomRepository {

}
