package com.untact.vo;

import com.untact.domain.member.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResultVO {
	private String token;
	private MemberEntity member;
}
