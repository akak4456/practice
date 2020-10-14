package com.untact.domain.englishspelling;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of="esno")
public class EnglishSpelling {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long esno;//영어 철자 번호
	
	@Column(name="spelling",unique=true)
	private String spelling;//영어 철자
}
