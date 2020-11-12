package com.untact.domain.wordincorrectanswernote;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.untact.domain.board.Board;
import com.untact.domain.board.Kind;
import com.untact.domain.englishspelling.EnglishSpelling;
import com.untact.domain.group.GroupEntity;
import com.untact.domain.member.MemberEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
@SequenceGenerator(name="wian_seq", initialValue=1, allocationSize=1)
@EqualsAndHashCode(of="wianno")
public class WordIncorrectAnswerNote {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="wian_seq")
	private Long wianno;//단어오답노트 번호
	
	private String reason;//단어 틀린 이유
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn
	private EnglishSpelling englishSpelling;
	public void setEnglishSpelling(EnglishSpelling englishSpelling) {
		this.englishSpelling = englishSpelling;
	}
}
