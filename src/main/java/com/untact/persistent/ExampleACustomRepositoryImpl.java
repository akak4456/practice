package com.untact.persistent;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

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
		if(clazz == ExampleA.class) {
			QExampleA exampleA = QExampleA.exampleA;
			return from(exampleA).fetch();
		}else if(clazz == ExampleASpecial1.class) {
			QExampleASpecial1 exampleA = QExampleASpecial1.exampleASpecial1;
			return from(exampleA).fetch();
		}
		return null;
	}
}
