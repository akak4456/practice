package com.untact.persistent;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.JPQLQuery;
import com.untact.domain.ExampleA;
import com.untact.domain.ExampleASpecial1;
import com.untact.domain.QExampleA;
import com.untact.domain.QExampleASpecial1;

public class ExampleACustomRepositoryImpl extends QuerydslRepositorySupport implements ExampleACustomRepository {
	
	public ExampleACustomRepositoryImpl() {
		super(ExampleA.class);
		// TODO Auto-generated constructor stub
	}
	@Override
	public List<? extends ExampleA> getAllThing(Class<? extends ExampleA> clazz) {
		// TODO Auto-generated method stub
		JPQLQuery<? extends ExampleA> query = makeJPQLQuery(clazz);
		return query.fetch();
		
	}
	private JPQLQuery<? extends ExampleA> makeJPQLQuery(Class<? extends ExampleA> clazz){
		if(clazz == ExampleA.class) {
			return from(QExampleA.exampleA);
		}else if(clazz == ExampleASpecial1.class) {
			return from(QExampleASpecial1.exampleASpecial1);
		}
		return null;
	}
}
