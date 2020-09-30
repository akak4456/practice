package com.untact.persistent;

import org.springframework.data.jpa.repository.JpaRepository;

import com.untact.domain.ExampleA;

public interface ExampleARepository extends JpaRepository<ExampleA, Long>,ExampleACustomRepository {

}
