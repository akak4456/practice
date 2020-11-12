package com.untact.domain.grouprule;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.untact.domain.group.GroupEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
@SequenceGenerator(name="gr_seq", initialValue=1, allocationSize=1)
@EqualsAndHashCode(of="grno")
public class GroupRule {
	/*
	 이 엔티티는 시스템이 제공하는 규칙 이외의 규칙을 사용자들끼리
	 정할 때 그 규칙을 저장하는 것이다.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="gr_seq")
	private Long grno;//그룹 룰 번호
	
	private String ruleTitle;//그룹 룰 명칭
	
	private String ruleContent;//그룹 룰 내용
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@CreationTimestamp
	private LocalDateTime regdate;//그룹 룰 생성 시간
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@UpdateTimestamp
	private LocalDateTime updatedate;//그룹 룰 수정 시간
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn
	private GroupEntity group;//이 룰은 어떤 그룹에 속할 것이냐?
	public void setGroup(GroupEntity group) {
		this.group = group;
	}
}
