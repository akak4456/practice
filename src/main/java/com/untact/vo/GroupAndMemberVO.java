package com.untact.vo;

import com.untact.domain.group.GroupEntity;
import com.untact.domain.member.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupAndMemberVO {
	private GroupEntity group;
	private MemberEntity member;
}
