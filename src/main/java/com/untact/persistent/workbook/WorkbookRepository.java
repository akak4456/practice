package com.untact.persistent.workbook;

import org.springframework.data.jpa.repository.JpaRepository;

import com.untact.domain.workbook.Workbook;

public interface WorkbookRepository extends JpaRepository<Workbook, Long>, WorkbookCustomRepository {

}
