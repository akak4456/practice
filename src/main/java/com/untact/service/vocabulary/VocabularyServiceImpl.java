package com.untact.service.vocabulary;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.untact.domain.englishdictionary.EnglishDictionary;
import com.untact.domain.englishspelling.EnglishSpelling;
import com.untact.domain.group.GroupEntity;
import com.untact.domain.member.MemberEntity;
import com.untact.domain.vocabulary.Vocabulary;
import com.untact.domain.vocabularyitem.VocabularyItem;
import com.untact.persistent.englishdictionary.EnglishDictionaryRepository;
import com.untact.persistent.englishspelling.EnglishSpellingRepository;
import com.untact.persistent.group.GroupEntityRepository;
import com.untact.persistent.vocabulary.VocabularyRepository;
import com.untact.persistent.vocabularyitem.VocabularyItemRepository;
import com.untact.vo.PageVO;
import com.untact.vo.VocabularyPageVO;
@Service
public class VocabularyServiceImpl implements VocabularyService {
	@Autowired
	private GroupEntityRepository groupRepo;
	@Autowired
	private VocabularyRepository vocabularyRepo;
	@Autowired
	private VocabularyItemRepository vocabularyItemRepo;
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
	@Override
	public boolean addVoca(Long gno,MemberEntity member,String title, List<String> content) {
		GroupEntity group = groupRepo.findById(gno).get();
		List<EnglishSpelling> alreadyExistWords = englishSpellingRepo.findByTargetSpellingList(content);
		Vocabulary vocabulary = Vocabulary.builder()
									.group(group)
									.member(member)
									.title(title)
									.cnt(Long.valueOf(alreadyExistWords.size()))
									.build();
		vocabularyRepo.save(vocabulary);
		List<VocabularyItem> items = new ArrayList<>();
		for(EnglishSpelling spell:alreadyExistWords) {
			VocabularyItem item = VocabularyItem.builder()
									.group(group)
									.member(member)
									.vocabulary(vocabulary)
									.englishSpelling(spell)
									.build();
			items.add(item);
		}
		vocabularyItemRepo.saveAll(items);
		//서버에 없는 단어는 어떻게 해야 할까?
		return true;
	}

}
