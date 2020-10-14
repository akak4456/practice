package com.untact.persistent.englishspelling;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.untact.domain.englishspelling.EnglishSpelling;

public interface EnglishSpellingRepository
		extends JpaRepository<EnglishSpelling, Long>, EnglishSpellingCustomRepository {
	@Query("SELECT spell FROM EnglishSpelling spell WHERE spell.spelling in :newSpellings")
	public List<EnglishSpelling> EnglishSpellingListAlreadyExistInDBAmongTargetSpellingList(@Param("newSpellings")List<String> targetSpellings);

}
