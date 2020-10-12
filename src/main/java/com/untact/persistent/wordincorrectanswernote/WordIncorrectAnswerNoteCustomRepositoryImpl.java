package com.untact.persistent.wordincorrectanswernote;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.untact.domain.wordincorrectanswernote.WordIncorrectAnswerNote;

public class WordIncorrectAnswerNoteCustomRepositoryImpl extends QuerydslRepositorySupport
		implements WordIncorrectAnswerNoteCustomRepository {
	public WordIncorrectAnswerNoteCustomRepositoryImpl() {
		super(WordIncorrectAnswerNote.class);
	}
}
