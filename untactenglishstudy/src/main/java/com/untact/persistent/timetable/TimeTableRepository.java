package com.untact.persistent.timetable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.untact.domain.timetable.TimeTable;

public interface TimeTableRepository extends JpaRepository<TimeTable, Long>, TimeTableCustomRepository {

}
