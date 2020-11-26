package com.untact.service.vocabulary;

import org.springframework.data.domain.Page;

import com.untact.domain.englishdictionary.EnglishDictionary;
import com.untact.vo.PageVO;
import com.untact.vo.VocabularyPageVO;

public interface VocabularyService {
	public VocabularyPageVO getListWithPagingAndGroupNumberAndMemberNumber(PageVO pageVO,Long gno,Long mno);
	public Page<EnglishDictionary> getVocabularyItemsWithPagingAndVocabularyNumber(PageVO pageVO,Long vno);
}
