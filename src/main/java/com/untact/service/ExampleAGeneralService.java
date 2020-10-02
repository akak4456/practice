package com.untact.service;

import org.springframework.stereotype.Service;

import com.untact.domain.ExampleA;

@Service
public class ExampleAGeneralService extends ExampleAService<ExampleA> {

	public ExampleAGeneralService() {
		super(ExampleA.class);
		// TODO Auto-generated constructor stub
	}
}
