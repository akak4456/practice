package com.untact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.untact.domain.ExampleA;
import com.untact.persistent.ExampleARepository;

public abstract class ExampleAService<T extends ExampleA> {
	private Class<? extends ExampleA> clazz;
	@Autowired
	private ExampleARepository repo;
	public ExampleAService(Class<? extends ExampleA> clazz) {
		this.clazz = clazz;
	}
	public List<? extends ExampleA> getList(){
		return repo.getAllThing(clazz);
	}
}
