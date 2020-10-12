package com.untact.persistent.wordincorrectanswernote;

import org.springframework.data.jpa.repository.JpaRepository;

import com.untact.domain.wordincorrectanswernote.WordIncorrectAnswerNote;

public interface WordIncorrectAnswerNoteRepository
		extends JpaRepository<WordIncorrectAnswerNote, Long>, WordIncorrectAnswerNoteCustomRepository {

}
