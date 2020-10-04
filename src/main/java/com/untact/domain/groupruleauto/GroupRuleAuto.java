package com.untact.domain.groupruleauto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@SequenceGenerator(name="gra_seq", initialValue=1, allocationSize=1)
@EqualsAndHashCode(of="grano")
public class GroupRuleAuto {
	/*
	 이 엔티티는 시스템이 제공하는 규칙을 저장하는 것이다.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="gr_seq")
	private Long grano;//그룹 룰 자동 번호
	
	@Enumerated(EnumType.ORDINAL)
	@NotNull
	private IsLateFine isLateFine;//사용자에게 지각 벌금을 물것인가?
	
	private Long LateFineAmount;//지각 벌금을 문다면 그 금액은?
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@CreationTimestamp
	private LocalDateTime regdate;//그룹 룰 자동 생성 시간
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@UpdateTimestamp
	private LocalDateTime updatedate;//그룹 룰 자동 수정 시간
	
	@OneToOne
	@JoinColumn
	private GroupEntity group;//이 그룹 룰 자동은 어느 그룹에 속하는가?
	public void setGroup(GroupEntity group) {
		this.group = group;
	}
}
