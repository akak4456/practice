package com.untact.domain.englishdictionary;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
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
public class EnglishDictionary {
	@EmbeddedId
	private EnglishDictionaryId id;
	
	@MapsId("spelling")
	@ManyToOne
	@JoinColumn(name="spelling")
	private EnglishSpelling englishSpelling;
	public void setEnglishSpelling(EnglishSpelling englishSpelling) {
		this.englishSpelling = englishSpelling;
	}
}
