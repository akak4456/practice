package com.untact.service.vocabulary;

import java.util.List;

import com.untact.domain.member.MemberEntity;
import com.untact.vo.NumberOfSuccessesOrFailures;

public interface VocabularyService {
	public NumberOfSuccessesOrFailures addVocabulary(List<String> words,Long gno,MemberEntity member);
	public void deleteVocabulary(List<String> deleted,Long gno,MemberEntity member);
}
