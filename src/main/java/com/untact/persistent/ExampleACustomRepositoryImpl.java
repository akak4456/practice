package com.untact.persistent;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.untact.domain.ExampleA;
import com.untact.domain.ExampleASpecial1;
import com.untact.domain.QExampleA;
import com.untact.domain.QExampleASpecial1;

public class ExampleACustomRepositoryImpl extends QuerydslRepositorySupport implements ExampleACustomRepository {
	private QExampleA exampleA = QExampleA.exampleA;
	
	private QExampleASpecial1 exampleASpecial1 = QExampleASpecial1.exampleASpecial1;
	
	public ExampleACustomRepositoryImpl() {
		super(ExampleA.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ExampleA> getAllThing() {
		// TODO Auto-generated method stub
		return from(exampleA).fetch();
	}

	@Override
	public List<ExampleASpecial1> getSpeical1() {
		// TODO Auto-generated method stub
		return from(exampleASpecial1).fetch();
	}

}
