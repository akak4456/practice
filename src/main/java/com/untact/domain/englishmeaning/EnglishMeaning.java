package com.untact.domain.englishmeaning;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.untact.domain.englishspelling.EnglishSpelling;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
@SequenceGenerator(name="em_seq", initialValue=1, allocationSize=1)
@EqualsAndHashCode(of="emno")
public class EnglishMeaning {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="em_seq")
	private Long emno;//영어 뜻 번호
	
	private String meaning;//영어 뜻
	
	@ManyToOne
	@JoinColumn
	private EnglishSpelling englishSpelling;
	public void setEnglishSpelling(EnglishSpelling englishSpelling) {
		this.englishSpelling = englishSpelling;
	}
}
