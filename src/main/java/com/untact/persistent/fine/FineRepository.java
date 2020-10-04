package com.untact.persistent.fine;

import org.springframework.data.jpa.repository.JpaRepository;

import com.untact.domain.fine.Fine;

public interface FineRepository extends JpaRepository<Fine, Long>, FineCustomRepository {

}
