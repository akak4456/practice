package com.untact.domain.score;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

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
@DiscriminatorColumn(name="DTYPE")
@SequenceGenerator(name="s_seq", initialValue=1, allocationSize=1)
@EqualsAndHashCode(of="sno")
public class Score {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="s_seq")
	private Long sno;//점수 번호
	
	private Long scoreAmount;//점수 크기
	
	@ManyToOne
	@JoinColumn
	private GroupEntity group;//이 점수는 어떤 그룹에 속하나?
	public void setGroup(GroupEntity group) {
		this.group = group;
	}
	
	@ManyToOne
	@JoinColumn
	private MemberEntity member;//이 멤버의 점수 크기는 무엇인가?
	public void setMember(MemberEntity member) {
		this.member = member;
	}
}
