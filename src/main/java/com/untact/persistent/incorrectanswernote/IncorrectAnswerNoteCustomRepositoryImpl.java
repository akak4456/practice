package com.untact.persistent.incorrectanswernote;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.untact.domain.incorrectanswernote.IncorrectAnswerNote;

public class IncorrectAnswerNoteCustomRepositoryImpl extends QuerydslRepositorySupport
		implements IncorrectAnswerNoteCustomRepository {
	public IncorrectAnswerNoteCustomRepositoryImpl() {
		super(IncorrectAnswerNote.class);
	}
}
