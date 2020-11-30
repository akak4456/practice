package com.untact.domain.englishspelling;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of="spelling")
public class EnglishSpelling {
	@Id
	@Column(length=50)
	private String spelling;//영어 철자
	
	private String lv;//단어 난이도(일단은 String으로 저장함 나중에 변경 가능성 있음)
}
