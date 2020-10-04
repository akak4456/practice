package com.untact.persistent.connectionrecord;

import org.springframework.data.jpa.repository.JpaRepository;

import com.untact.domain.connectionrecord.ConnectionRecord;

public interface ConnectionRecordRepository
		extends JpaRepository<ConnectionRecord, Long>, ConnectionRecordCustomRepository {

}
