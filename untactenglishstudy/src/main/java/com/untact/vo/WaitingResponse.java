package com.untact.vo;

import java.util.List;

import com.untact.domain.groupinclude.GroupInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WaitingResponse {
	private List<GroupInclude> waitings;
}
