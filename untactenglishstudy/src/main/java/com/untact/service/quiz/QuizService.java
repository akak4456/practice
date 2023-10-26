package com.untact.service.quiz;

import java.util.List;
import java.util.Optional;

import com.untact.domain.member.MemberEntity;
import com.untact.domain.workbook.Workbook;
import com.untact.vo.QuizResponse;

public interface QuizService {
	public Optional<QuizResponse> generateQuiz(Long gno, MemberEntity member, Long vno, String kind, Long count,String difficulty);
	public List<Workbook> generateRandomQuiz(Long cnt);
}
