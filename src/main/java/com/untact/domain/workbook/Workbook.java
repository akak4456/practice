package com.untact.domain.workbook;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import com.untact.domain.englishspelling.EnglishSpelling;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
@SequenceGenerator(name="wb_seq", initialValue=1, allocationSize=1)
@EqualsAndHashCode(of="wbno")
public class Workbook {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="wb_seq")
	private Long wbno;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="spelling")
	private EnglishSpelling englishSpelling;
	public void setEnglishSpelling(EnglishSpelling englishSpelling) {
		this.englishSpelling = englishSpelling;
	}
	
	private String question;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private WorkbookKind kind;
	
	private String ans1;
	
	private String ans2;
	
	private String ans3;
	
	private String ans4;
}
