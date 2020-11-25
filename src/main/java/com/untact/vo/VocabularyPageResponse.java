package com.untact.vo;

import com.untact.domain.vocabulary.Vocabulary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VocabularyPageResponse {
	private PageMaker<Vocabulary> page;
	private Long defaultVocaCount;
}
