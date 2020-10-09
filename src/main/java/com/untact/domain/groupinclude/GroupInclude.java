package com.untact.domain.groupinclude;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.untact.domain.group.GroupEntity;
import com.untact.domain.groupwaiting.GroupWaiting;
import com.untact.domain.groupwaiting.GroupWaitingStatus;
import com.untact.domain.member.MemberEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
@SequenceGenerator(name="gi_seq", initialValue=1, allocationSize=1)
@EqualsAndHashCode(of="gino")
public class GroupInclude {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="gi_seq")
	private Long gino;//그룹 포함 번호
	
	@ManyToOne
	@JoinColumn
	private GroupEntity group;//그룹 포함과 그룹과의 관계
	public void setGroup(GroupEntity group) {
		this.group = group;
	}
	
	@ManyToOne
	@JoinColumn
	private MemberEntity member;//사용자와 그룹 포함과의 관계
	public void setMember(MemberEntity member) {
		this.member = member;
	}
}
