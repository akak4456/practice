package com.untact.domain.member;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.sun.istack.NotNull;
import com.untact.domain.group.GroupEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
@SequenceGenerator(name="m_seq", initialValue=1, allocationSize=1)
@EqualsAndHashCode(of="mno")
public class MemberEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="m_seq")
	private Long mno;//멤버 번호
	
	@Column(unique=true)
	private String email;//이메일 주소
	
	private String password;//비밀 번호
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private Role role;//사용자인가 관리자인가?
	
	
	private Long recentQuizScore;//최근 퀴즈 성적
	
	private Long recentRankScore;//최근 랭킹 성적

	@ManyToOne
	@JoinColumn
	private GroupEntity group;//이 사용자는 어느 그룹에 속하는가?
	public void setGroup(GroupEntity group) {
		this.group = group;
	}
}
