package com.untact.persistent.workbook;

import java.util.List;

import com.untact.domain.englishspelling.EnglishSpelling;
import com.untact.domain.englishspelling.EnglishSpellingDifficulty;
import com.untact.domain.workbook.Workbook;
import com.untact.domain.workbook.WorkbookKind;

public interface WorkbookCustomRepository {
	public List<Workbook> findBywordListAndKind(List<EnglishSpelling> words,WorkbookKind kind);
	public List<Workbook> findByKind(WorkbookKind kind);
	public List<Workbook> findByKindAndDifficulty(WorkbookKind kind,EnglishSpellingDifficulty difficulty);
}
