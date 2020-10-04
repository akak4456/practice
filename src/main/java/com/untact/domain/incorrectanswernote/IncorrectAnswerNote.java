package com.untact.domain.incorrectanswernote;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.untact.domain.board.Board;
import com.untact.domain.board.Kind;
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
@SequenceGenerator(name="ian_seq", initialValue=1, allocationSize=1)
@EqualsAndHashCode(of="ianno")
public class IncorrectAnswerNote {
	/*
	 나중에 의존 관계를 명시할 필요가 있음
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="ian_seq")
	private Long ianno;//오답노트 번호
	
	private String spelling;//오답노트 스펠링
	
	private String note;//오답노트 노트
}
