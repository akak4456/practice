package com.untact.domain.englishspelling;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.untact.domain.deposit.Deposit;
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
@SequenceGenerator(name="es_seq", initialValue=1, allocationSize=1)
@EqualsAndHashCode(of="esno")
public class EnglishSpelling {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="es_seq")
	private Long esno;//영어 철자 번호
	
	private String spelling;//영어 철자
}
