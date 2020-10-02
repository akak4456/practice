package com.untact.persistent;

import org.springframework.data.jpa.repository.JpaRepository;

import com.untact.domain.ExampleA;

/*
 만약에 ExampleARepository에 기능을 넣고 싶으면
 ExampleACustomRepository에 메서드를 추가한 뒤에
 ExampleACustomRepositroyImpl에서 구현하자
 */
public interface ExampleARepository extends JpaRepository<ExampleA, Long>,ExampleACustomRepository {

}
