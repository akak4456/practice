package com.untact.persistent.englishspelling;

import org.springframework.data.jpa.repository.JpaRepository;

import com.untact.domain.englishspelling.EnglishSpelling;

public interface EnglishSpellingRepository
		extends JpaRepository<EnglishSpelling, Long>, EnglishSpellingCustomRepository {

}
