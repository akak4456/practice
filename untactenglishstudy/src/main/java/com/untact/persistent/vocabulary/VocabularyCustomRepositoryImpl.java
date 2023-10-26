package com.untact.persistent.vocabulary;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import com.untact.domain.vocabulary.QVocabulary;
import com.untact.domain.vocabulary.Vocabulary;

public class VocabularyCustomRepositoryImpl extends QuerydslRepositorySupport implements VocabularyCustomRepository {
	public VocabularyCustomRepositoryImpl() {
		super(Vocabulary.class);
	}

	@Override
	public Page<Vocabulary> getPageWithGroupNumberAndMemberNumber(Pageable pageable, Long gno,Long mno) {
		QVocabulary vocabulary = QVocabulary.vocabulary;
		JPQLQuery<Tuple> query = from(vocabulary).select(vocabulary.vno,vocabulary.title,vocabulary.cnt);
		query.where(vocabulary.group.gno.eq(gno).and(vocabulary.member.mno.eq(mno)));
		Long totalCount = query.fetchCount();
		List<Tuple> list = getQuerydsl().applyPagination(pageable, query).fetch();
		List<Vocabulary> vocabularyList = new ArrayList<>();
		for(Tuple t:list) {
			vocabularyList.add(Vocabulary.builder()
					.vno(t.get(vocabulary.vno))
					.title(t.get(vocabulary.title))
					.cnt(t.get(vocabulary.cnt))
					.build());
		}
		return new PageImpl<Vocabulary>(vocabularyList,pageable,totalCount);
	}
}
