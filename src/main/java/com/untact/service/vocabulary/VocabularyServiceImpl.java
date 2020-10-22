package com.untact.service.vocabulary;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.untact.domain.englishspelling.EnglishSpelling;
import com.untact.domain.member.MemberEntity;
import com.untact.domain.vocabulary.Vocabulary;
import com.untact.persistent.englishspelling.EnglishSpellingRepository;
import com.untact.persistent.group.GroupEntityRepository;
import com.untact.persistent.vocabulary.VocabularyRepository;
import com.untact.vo.NumberOfSuccessesOrFailures;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Service
@NoArgsConstructor
@AllArgsConstructor
public class VocabularyServiceImpl implements VocabularyService {
	@Autowired
	private EnglishSpellingRepository spellingRepo;
	@Autowired
	private GroupEntityRepository groupEntityRepo;
	@Autowired
	private VocabularyRepository vocabularyRepo;
	@Override
	public NumberOfSuccessesOrFailures addVocabulary(List<String> words,Long gno,MemberEntity member) {
		List<EnglishSpelling> alreadyWords = spellingRepo.findByTargetSpellingList(words);
		List<Vocabulary> vocabularyList = new ArrayList<>();
		for(EnglishSpelling alreadyWord:alreadyWords) {
			vocabularyList.add(new Vocabulary().builder().englishSpelling(alreadyWord).group(groupEntityRepo.findById(gno).get()).member(member).build());
		}
		vocabularyRepo.saveAll(vocabularyList);
		return new NumberOfSuccessesOrFailures(alreadyWords.size(),words.size()-alreadyWords.size());
	}
	@Override
	public void deleteVocabulary(List<String> deleted, Long gno, MemberEntity member) {
		vocabularyRepo.deleteByGroupNumberAndMemberNumberAndWordList(gno, member.getMno(), deleted);
		
	}

}
