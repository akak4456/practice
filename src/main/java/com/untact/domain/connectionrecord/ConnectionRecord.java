package com.untact.domain.connectionrecord;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.untact.domain.member.MemberEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
@SequenceGenerator(name="c_seq", initialValue=1, allocationSize=1)
@EqualsAndHashCode(of="cno")
public class ConnectionRecord {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="c_seq")
	private Long cno;//접속 기록 번호
	
	private LocalDateTime connectionDate;//접속 일
	
	@ManyToOne
	@JoinColumn
	private MemberEntity member;//어떤 사용자가 언제 접근했는지 나타냄
	public void setMember(MemberEntity member) {
		this.member = member;
	}
}
