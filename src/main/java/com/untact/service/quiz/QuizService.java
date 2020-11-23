package com.untact.service.quiz;

import java.util.Optional;

import com.untact.domain.member.MemberEntity;
import com.untact.vo.QuizResponse;

public interface QuizService {
	public Optional<QuizResponse> generateQuiz(Long gno, MemberEntity member, Long vno, String kind, Long count);
}
