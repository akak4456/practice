package com.untact.service;

import org.springframework.stereotype.Service;

import com.untact.domain.ExampleA;
import com.untact.domain.ExampleASpecial1;

@Service
public class ExampleASpecial1Service extends ExampleAService<ExampleASpecial1> {

	public ExampleASpecial1Service() {
		super(ExampleASpecial1.class);
		// TODO Auto-generated constructor stub
	}

}
