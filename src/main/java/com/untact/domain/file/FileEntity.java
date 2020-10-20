package com.untact.domain.file;

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
@SequenceGenerator(name="file_seq", initialValue=1, allocationSize=1)
@EqualsAndHashCode(of="fileno")
public class FileEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="file_seq")
	private Long fileno;//파일 번호
	
	private String path;//파일 경로
}
