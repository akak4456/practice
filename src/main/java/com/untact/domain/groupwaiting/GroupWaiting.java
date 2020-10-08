package com.untact.domain.groupwaiting;

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
import com.untact.domain.member.MemberEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
@SequenceGenerator(name="gw_seq", initialValue=1, allocationSize=1)
@EqualsAndHashCode(of="gwno")
public class GroupWaiting {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="gw_seq")
	private Long gwno;//그룹 웨이팅 번호
	
	@Enumerated(EnumType.ORDINAL)
	@NotNull
	private GroupWaitingStatus status;//그룹 웨이팅 상태
	
	@ManyToOne
	@JoinColumn
	private GroupEntity group;//그룹 웨이팅과 그룹과의 관계
	public void setGroup(GroupEntity group) {
		this.group = group;
	}
	
	@ManyToOne
	@JoinColumn
	private MemberEntity member;//사용자와 그룹과의 관계
	public void setMember(MemberEntity member) {
		this.member = member;
	}
}
