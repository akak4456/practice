package com.untact.domain.attendance;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@SequenceGenerator(name="a_seq", initialValue=1, allocationSize=1)
@EqualsAndHashCode(of="ano")
public class Attendance {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="a_seq")
	private Long ano;//출석 번호
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private AttendanceStatus status;//출석 상태(출석, 지각, 결석)
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@CreationTimestamp
	private LocalDateTime regdate;//출석 등록 시간
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="gno")
	@JsonIgnore
	private GroupEntity group;//그룹과의 관계
	public void setGroup (GroupEntity group) {
		this.group = group;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="mno")
	@JsonIgnore
	private MemberEntity member;//멤버와의 관계
	public void setMember(MemberEntity member) {
		this.member = member;	
	}
}

