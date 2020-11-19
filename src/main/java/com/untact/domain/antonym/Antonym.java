package com.untact.domain.antonym;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.untact.domain.englishspelling.EnglishSpelling;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "wordfrom", "wordto" }))
@SequenceGenerator(name="an_seq", initialValue=1, allocationSize=1)
@EqualsAndHashCode(of="anno")
public class Antonym {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="an_seq")
	private Long anno;
	@ManyToOne
	@JoinColumn(name="wordfrom")
	private EnglishSpelling englishSpelling;
	public void setEnglishSpelling(EnglishSpelling englishSpelling) {
		this.englishSpelling = englishSpelling;
	}
	
	private String wordto;
}
