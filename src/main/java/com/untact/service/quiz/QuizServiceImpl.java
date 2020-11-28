package com.untact.service.quiz;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.untact.domain.englishspelling.EnglishSpelling;
import com.untact.domain.member.MemberEntity;
import com.untact.domain.workbook.Workbook;
import com.untact.domain.workbook.WorkbookKind;
import com.untact.persistent.englishspelling.EnglishSpellingRepository;
import com.untact.persistent.vocabularyitem.VocabularyItemRepository;
import com.untact.persistent.workbook.WorkbookRepository;
import com.untact.vo.QuizResponse;
import com.untact.vo.QuizVO;

@Service
public class QuizServiceImpl implements QuizService {
	public static final int MINIMUM_VOCABULARY_ITEM_COUNT = 3;
	@Autowired
	private EnglishSpellingRepository englishSpellingRepo;
	@Autowired
	private VocabularyItemRepository vocabularyItemRepo;
	@Autowired
	private WorkbookRepository workbookRepo;
	@Override
	public Optional<QuizResponse> generateQuiz(Long gno, MemberEntity member, Long vno, String kind, Long count) {
		WorkbookKind retKind = WorkbookKind.valueOf(kind);
		Random random = new Random();
		List<EnglishSpelling> words = null;
		if(vno == 0L) {
			//사용자가 기본 단어장을 이용해 퀴즈를 만들기를 원하면
			words = englishSpellingRepo.findAll();
		}else {
			words = vocabularyItemRepo.findEnglishSpellingByVocaburaryNumber(vno);
		}
		
		if(words.size() < MINIMUM_VOCABULARY_ITEM_COUNT) {
			//단어장에 있는 단어의 개수가 너무 적으면
			return Optional.empty();
		}
		
		Map<EnglishSpelling,List<Workbook>> cand = workbookRepo.findBywordListAndKind(words, retKind).stream().collect(Collectors.groupingBy(Workbook::getEnglishSpelling));
		List<EnglishSpelling> spellingList = new ArrayList<>(cand.keySet());
		if(spellingList.size() < count) {
			return Optional.empty();
		}
		Set<Integer> problemIdx = new HashSet<>();
		while(problemIdx.size() < count) {
			problemIdx.add(random.nextInt(spellingList.size()));
		}
		List<QuizVO> vo = new ArrayList<>();
		for(Integer idx:problemIdx) {
			List<Workbook> wList = cand.get(spellingList.get(idx));
			Workbook w = wList.get(random.nextInt(wList.size()));
			vo.add(new QuizVO(w.getQuestion(),w.getAns1(),w.getAns2(),w.getAns3(),w.getAns4(),w.getEnglishSpelling().getSpelling()));
		}
		return Optional.of(new QuizResponse(vo));
	}
	@Override
	public List<Workbook> generateRandomQuiz(Long cnt) {
		return workbookRepo.findByRandom(cnt);
	}
}
