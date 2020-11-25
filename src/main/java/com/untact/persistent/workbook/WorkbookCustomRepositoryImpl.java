package com.untact.persistent.workbook;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.JPQLQuery;
import com.untact.domain.englishspelling.EnglishSpelling;
import com.untact.domain.englishspelling.QEnglishSpelling;
import com.untact.domain.workbook.QWorkbook;
import com.untact.domain.workbook.Workbook;
import com.untact.domain.workbook.WorkbookKind;

public class WorkbookCustomRepositoryImpl extends QuerydslRepositorySupport implements WorkbookCustomRepository {
	public WorkbookCustomRepositoryImpl() {
		super(Workbook.class);
	}

	@Override
	public List<Workbook> findBywordListAndKind(List<EnglishSpelling> words, WorkbookKind kind) {
		QWorkbook workbook = QWorkbook.workbook;
		QEnglishSpelling spelling = QEnglishSpelling.englishSpelling;
		JPQLQuery<Workbook> query = from(workbook);
		query.innerJoin(workbook.englishSpelling,spelling);
		query.where(workbook.englishSpelling.in(words).and(workbook.kind.eq(kind)));
		return query.fetch();
	}

	@Override
	public List<Workbook> findByKind(WorkbookKind kind) {
		QWorkbook workbook = QWorkbook.workbook;
		QEnglishSpelling spelling = QEnglishSpelling.englishSpelling;
		JPQLQuery<Workbook> query = from(workbook);
		query.innerJoin(workbook.englishSpelling,spelling);
		query.where(workbook.kind.eq(kind));
		return query.fetch();
	}
}
