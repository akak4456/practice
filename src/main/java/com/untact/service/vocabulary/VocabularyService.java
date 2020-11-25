package com.untact.service.vocabulary;

import com.untact.vo.PageVO;
import com.untact.vo.VocabularyPageVO;

public interface VocabularyService {
	public VocabularyPageVO getListWithPagingAndGroupNumberAndMemberNumber(PageVO pageVO,Long gno,Long mno);
}
