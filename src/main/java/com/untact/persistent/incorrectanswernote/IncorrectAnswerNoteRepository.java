package com.untact.persistent.incorrectanswernote;

import org.springframework.data.jpa.repository.JpaRepository;

import com.untact.domain.incorrectanswernote.IncorrectAnswerNote;

public interface IncorrectAnswerNoteRepository
		extends JpaRepository<IncorrectAnswerNote, Long>, IncorrectAnswerNoteCustomRepository {

}
