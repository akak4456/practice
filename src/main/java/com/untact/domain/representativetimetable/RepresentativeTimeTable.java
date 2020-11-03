package com.untact.domain.representativetimetable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.untact.domain.board.Board;
import com.untact.domain.group.GroupEntity;
import com.untact.domain.member.MemberEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
@SequenceGenerator(name="rt_seq", initialValue=1, allocationSize=1)
@EqualsAndHashCode(of="rtno")
public class RepresentativeTimeTable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="rt_seq")
	private Long rtno;//시간표 번호
	
	private String title;//시간표 제목
	
	public RepresentativeTimeTable modifyThisToTargetTimeTable(RepresentativeTimeTable targetTimeTable) {
		this.title = targetTimeTable.title;
		return this;
	}
}
