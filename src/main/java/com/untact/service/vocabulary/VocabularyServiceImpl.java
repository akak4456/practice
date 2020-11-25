package com.untact.service.vocabulary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	@Override
	public VocabularyPageVO getListWithPagingAndGroupNumberAndMemberNumber(PageVO pageVO, Long gno,Long mno) {
		return new VocabularyPageVO(vocabularyRepo.getPageWithGroupNumberAndMemberNumber(pageVO.makePageable(0, "vno"), gno,mno),englishSpellingRepo.count());
	}

}
