package com.untact.persistent.representativetimetable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.untact.domain.representativetimetable.RepresentativeTimeTable;

public interface RepresentativeTimeTableRepository
		extends JpaRepository<RepresentativeTimeTable, Long>, RepresentativeTimeTableCustomRepository {

}
