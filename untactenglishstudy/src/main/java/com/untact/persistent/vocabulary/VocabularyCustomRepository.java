package com.untact.persistent.vocabulary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.untact.domain.vocabulary.Vocabulary;

public interface VocabularyCustomRepository {
	public Page<Vocabulary> getPageWithGroupNumberAndMemberNumber(Pageable pageable, Long gno,Long mno);
}
