package com.untact.service.vocabulary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.untact.domain.englishdictionary.EnglishDictionary;
import com.untact.persistent.englishdictionary.EnglishDictionaryRepository;
import com.untact.persistent.englishspelling.EnglishSpellingRepository;
import com.untact.persistent.vocabulary.VocabularyRepository;
import com.untact.vo.PageVO;
import com.untact.vo.VocabularyPageVO;
@Service
public class VocabularyServiceImpl implements VocabularyService {
	@Autowired
	private VocabularyRepository vocabularyRepo;
	@Autowired
	private EnglishSpellingRepository englishSpellingRepo;
	@Autowired
	private EnglishDictionaryRepository englishDictionaryRepo;
	@Override
	public VocabularyPageVO getListWithPagingAndGroupNumberAndMemberNumber(PageVO pageVO, Long gno,Long mno) {
		return new VocabularyPageVO(vocabularyRepo.getPageWithGroupNumberAndMemberNumber(pageVO.makePageable(0, "vno"), gno,mno),englishSpellingRepo.count());
	}
	@Override
	public Page<EnglishDictionary> getVocabularyItemsWithPagingAndVocabularyNumber(PageVO pageVO, Long vno) {
		return englishDictionaryRepo.getPageWithVocabularyNumber(pageVO.makePageable(0, "edno"), vno);
	}

}
