package com.untact.domain.fine;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@SequenceGenerator(name="f_seq", initialValue=1, allocationSize=1)
@EqualsAndHashCode(of="fno")

public class Fine {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="f_seq")
	private Long fno;// 벌금 번호
	
	private Long fineAmount;//벌금 액수
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@CreationTimestamp
	private LocalDateTime regdate;//벌금 생성 시간
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@UpdateTimestamp
	private LocalDateTime updatedate;//벌금 수정 시간
	
	@ManyToOne
	@JoinColumn
	private GroupEntity group;//벌금들은 어떤 그룹에 속했는지 나타냄
	public void setGroup(GroupEntity group) {
		this.group = group;
	}
	
	@ManyToOne
	@JoinColumn
	private MemberEntity member;//누가 벌금을 냈는지 나타냄
	public void setMember(MemberEntity member) {
		this.member = member;
	}
}
