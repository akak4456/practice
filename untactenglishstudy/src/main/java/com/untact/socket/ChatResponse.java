package com.untact.socket;

import java.util.List;

import com.untact.domain.member.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponse {
	private Long mno;
	private String from;
	private String msg;
	private String time;
	private List<MemberEntity> userList;
}
