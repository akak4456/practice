package com.untact.persistent.connectionrecord;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.untact.domain.connectionrecord.ConnectionRecord;

public class ConnectionRecordCustomRepositoryImpl extends QuerydslRepositorySupport
		implements ConnectionRecordCustomRepository {
	public ConnectionRecordCustomRepositoryImpl() {
		super(ConnectionRecord.class);
	}

}
