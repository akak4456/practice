package com.untact.persistent;

import java.util.List;

import com.untact.domain.ExampleA;

public interface ExampleACustomRepository{
	public List<? extends ExampleA> getAllThing(Class<? extends ExampleA> clazz);
}
