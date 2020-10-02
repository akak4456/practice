package com.untact.persistent;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.untact.demo.UntactenglishstudyApplication;
import com.untact.domain.ExampleA;
import com.untact.domain.ExampleASpecial1;
import com.untact.domain.ExampleASpecial2;

import lombok.extern.java.Log;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = UntactenglishstudyApplication.class)
@Transactional
@Commit
@Log
public class ExampleARepositoryTest {
	@Autowired
	private ExampleARepository repo;
	@Before
	public void setUp() {
		repo.deleteAllInBatch();
	}
	@Test
	public void test() {
		ExampleASpecial1 aspecial1_1 = ExampleASpecial1.builder().common("common_1").special1("aspecial1_1").build();
		ExampleASpecial1 aspecial1_2 = ExampleASpecial1.builder().common("common_2").special1("aspecial1_2").build();
		ExampleASpecial2 aspecial2_1 = ExampleASpecial2.builder().common("common_1").special2("aspecial2_1").build();
		ExampleASpecial2 aspecial2_2 = ExampleASpecial2.builder().common("common_2").special2("aspecial2_2").build();
		repo.save(aspecial1_1);
		repo.save(aspecial1_2);
		repo.save(aspecial2_1);
		repo.save(aspecial2_2);
		List<?> generalList = repo.getAllThing(ExampleA.class);
		assertEquals(generalList.size(),4);
		@SuppressWarnings("unchecked")
		/*
		 이렇게 막은 이유 주석으로 남기기
		 getAllThing에 ExampleASpecial1.class를 넘겼다.
		 그러면 이게 fetch를 하는데 이것은 List<ExampleASpecial1>과 같다.
		 그래서 아래와 같이 캐스트를 하였다.
		 */
		List<ExampleASpecial1> special1List = (List<ExampleASpecial1>) repo.getAllThing(ExampleASpecial1.class);
		assertEquals(special1List.size(),2);
		log.info(special1List.get(0).getCommon());
		if(special1List.get(0) instanceof ExampleASpecial1) {
			ExampleASpecial1 example = (ExampleASpecial1) special1List.get(0);
			log.info(example.getSpecial1());
		}
	}
}
