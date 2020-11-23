package com.untact.service.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.untact.domain.englishdictionary.EnglishDictionary;
import com.untact.domain.englishspelling.EnglishSpelling;
import com.untact.domain.workbook.Workbook;
import com.untact.domain.workbook.WorkbookKind;
import com.untact.persistent.englishdictionary.EnglishDictionaryRepository;
import com.untact.persistent.workbook.WorkbookRepository;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private EnglishDictionaryRepository englishDictionaryRepo;
	@Autowired
	private WorkbookRepository workbookRepo;
	@Override
	public void setupQuiz() {
		Map<EnglishSpelling,List<EnglishDictionary>> dic = englishDictionaryRepo.findAll()
														.stream()
														.collect(Collectors.groupingBy(EnglishDictionary::getEnglishSpelling));//사전 저장
		
		List<Workbook> workbooks = new ArrayList<>();
		//빈칸 유형 문제 생성
		for(EnglishSpelling spell:dic.keySet()) {
			for(EnglishDictionary d:dic.get(spell)) {
				workbooks.add(Workbook.builder()
								.englishSpelling(spell)
								.question(d.getPartOfSpeech()+"\n"+d.getMeaning())
								.kind(WorkbookKind.blank)
								.ans1("none")
								.ans2("none")
								.ans3("none")
								.ans4("none")
								.build());
			}
		}
		workbookRepo.saveAll(workbooks);
	}

}
