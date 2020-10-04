package com.untact.domain.position;

import java.time.LocalDateTime;

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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@DiscriminatorColumn(name="DTYPE")
@SequenceGenerator(name="p_seq", initialValue=1, allocationSize=1)
@EqualsAndHashCode(of="pno")
public class PositionEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="m_seq")
	private Long pno;//직위 번호
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private WhcihPosition whichPosition;//어떤 직위인가?
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@CreationTimestamp
	private LocalDateTime regdate;//직위 생성 시간
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@UpdateTimestamp
	private LocalDateTime updatedate;//직위 수정 시간
	
	@ManyToOne
	@JoinColumn
	private GroupEntity group;//이 직위는 어떤 그룹에 속하나?
	public void setGroup(GroupEntity group) {
		this.group = group;
	}
	
	@ManyToOne
	@JoinColumn
	private MemberEntity member;//이 멤버는 어떤 직위인가?
	public void setMember(MemberEntity member) {
		this.member = member;
	}
}
