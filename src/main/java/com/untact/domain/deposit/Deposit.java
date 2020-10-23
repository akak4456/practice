package com.untact.domain.deposit;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "gno", "mno" }))
@SequenceGenerator(name="d_seq", initialValue=1, allocationSize=1)
@EqualsAndHashCode(of="dno")
public class Deposit {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="d_seq")
	private Long dno;//예치금 번호
	
	private Long depositAmount;//예치 금액
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@CreationTimestamp
	private LocalDateTime regdate;//예치금 생성 시간
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@UpdateTimestamp
	private LocalDateTime updatedate;//예치금 수정 시간
	
	@ManyToOne
	@JoinColumn(name="gno")
	private GroupEntity group;//예치금들은 어떤 그룹에 속했는지 나타냄
	public void setGroup(GroupEntity group) {
		this.group = group;
	}
	
	@ManyToOne
	@JoinColumn(name="mno")
	private MemberEntity member;//누가 예치금을 냈는지 나타냄
	public void setMember(MemberEntity member) {
		this.member = member;
	}
	
	public Deposit changeDepositAmount(Long newAmount) {
		this.depositAmount = newAmount;
		return this;
	}
}
