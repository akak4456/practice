package com.untact.domain.incorrectanswernote;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="ian_seq")
	private Long ianno;//오답노트 번호
	
	private String question;//문제
	
	private String reason;//문제 틀린 이유
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn
	private GroupEntity group;
	public void setGroup(GroupEntity group) {
		this.group = group;
	}
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn
	private MemberEntity member;
	public void setMember(MemberEntity member) {
		this.member = member;
	}
}
