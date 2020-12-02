package com.untact.service.vocabulary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.untact.domain.englishdictionary.EnglishDictionary;
import com.untact.domain.englishspelling.EnglishSpelling;
import com.untact.domain.englishspelling.EnglishSpellingDifficulty;
import com.untact.domain.group.GroupEntity;
import com.untact.domain.member.MemberEntity;
import com.untact.domain.vocabulary.Vocabulary;
import com.untact.domain.vocabularyitem.VocabularyItem;
import com.untact.domain.workbook.Workbook;
import com.untact.domain.workbook.WorkbookKind;
import com.untact.persistent.englishdictionary.EnglishDictionaryRepository;
import com.untact.persistent.englishspelling.EnglishSpellingRepository;
import com.untact.persistent.group.GroupEntityRepository;
import com.untact.persistent.vocabulary.VocabularyRepository;
import com.untact.persistent.vocabularyitem.VocabularyItemRepository;
import com.untact.persistent.workbook.WorkbookRepository;
import com.untact.service.crawler.CrawlerService;
import com.untact.vo.PageVO;
import com.untact.vo.VocabularyPageVO;

import lombok.extern.java.Log;
@Service
@Log
public class VocabularyServiceImpl implements VocabularyService {
	@Autowired
	private CrawlerService crawlerService;
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
	@Autowired
	private WorkbookRepository workbookRepo;
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
		//서버에 없는 단어는 어떻게 해야 할까?
		Set<String> allWords = new HashSet<>(content);
		Set<String> existWords = new HashSet<>(alreadyExistWords.stream().map(w->w.getSpelling()).collect(Collectors.toList()));
		allWords.removeAll(existWords);
		for(String word:existWords) {
			log.info(word);
		}
		for(String word:allWords) {
			log.info(word);
		}
		if(allWords.size() == 6) {
			return false;
		}
		if(allWords.size() > 0) {
			saveNewWordAndMakeQuiz(allWords);
			for(EnglishSpelling spell:allWords.stream().map(w->EnglishSpelling.builder().spelling(w).build()).collect(Collectors.toList())) {
				VocabularyItem item = VocabularyItem.builder()
										.group(group)
										.member(member)
										.vocabulary(vocabulary)
										.englishSpelling(spell)
										.build();
				items.add(item);
			}
		}
		vocabularyItemRepo.saveAll(items);
		vocabularyRepo.updateCntByVocabularyNumber(Long.valueOf(items.size()), vocabulary.getVno());
		return true;
	}
	
	private void saveNewWordAndMakeQuiz(Set<String> notExistWord) {
		List<EnglishSpelling> saveSpellingList = notExistWord.stream().map(w->EnglishSpelling.builder()
																			.spelling(w)
																			.lv(EnglishSpellingDifficulty.sohard)
																			.build()).collect(Collectors.toList());
		List<EnglishDictionary> saveDicList = crawlerService.getMeaning(notExistWord);
		englishSpellingRepo.saveAll(saveSpellingList);
		englishDictionaryRepo.saveAll(saveDicList);
		Map<EnglishSpelling,List<EnglishDictionary>> newDic = saveDicList
				.stream()
				.filter(d->!d.getMeaning().equals("NOTFOUND"))
				.collect(Collectors.groupingBy(EnglishDictionary::getEnglishSpelling));
		List<List<EnglishDictionary>> wrongAnswerList = new ArrayList<>();
		for(int i=0;i<10;i++) {
			wrongAnswerList.add(englishDictionaryRepo.findByRandom(3L));
		}
		List<Workbook> workbooks = new ArrayList<>();
		Random random = new Random();
		for(EnglishSpelling spell:newDic.keySet()) {
			String rightMean = newDic.get(spell).get(0).getMeaning();
			String rightClass = newDic.get(spell).get(0).getPartOfSpeech();
			List<EnglishDictionary> wrong = wrongAnswerList.get(random.nextInt(10));
			workbooks.add(Workbook.builder()
					.englishSpelling(spell)
					.question(spell.getSpelling())
					.kind(WorkbookKind.spelltomean)
					.ans1(rightMean)
					.ans2(wrong.get(0).getMeaning())
					.ans3(wrong.get(1).getMeaning())
					.ans4(wrong.get(2).getMeaning())
					.build());

			workbooks.add(Workbook.builder()
					.englishSpelling(spell)
					.question(rightMean)
					.kind(WorkbookKind.meantospell)
					.ans1(spell.getSpelling())
					.ans2(wrong.get(0).getEnglishSpelling().getSpelling())
					.ans3(wrong.get(1).getEnglishSpelling().getSpelling())
					.ans4(wrong.get(2).getEnglishSpelling().getSpelling())
					.build());
			
			workbooks.add(Workbook.builder()
						.englishSpelling(spell)
						.question(rightClass+"\n"+rightMean)
						.kind(WorkbookKind.blank)
						.ans1(spell.getSpelling())
						.ans2("none")
						.ans3("none")
						.ans4("none")
						.build());
		}
		workbookRepo.saveAll(workbooks);
	}

}
