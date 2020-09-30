package com.untact.persistent;

import java.util.List;

import com.untact.domain.ExampleA;
import com.untact.domain.ExampleASpecial1;

public interface ExampleACustomRepository{
	public List<ExampleA> getAllThing();
	
	public List<ExampleASpecial1> getSpeical1();
}
