package com.untact.cron;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.untact.service.attendance.AttendanceService;

import lombok.extern.java.Log;

@Component
@Log
public class AttendanceScheduler {
	@Autowired
	private AttendanceService attendanceService;
	@Scheduled(cron = "0 * * * * *")
	public void everyMinuteRun() {
		//매 분마다 돌아가는 알고리즘
		//즉 0초가 될때마다 실행됨
		log.info("MINUTE");
		LocalDateTime curTime = LocalDateTime.now();
		int day = curTime.getDayOfWeek().getValue()-1;//Mon-1 Sun-7 이므로 거기에다 1을 빼야 함
		int hour = curTime.getHour();
		int minute = curTime.getMinute();
	}
}
