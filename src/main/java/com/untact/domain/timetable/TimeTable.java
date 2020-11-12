package com.untact.domain.timetable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
@SequenceGenerator(name="t_seq", initialValue=1, allocationSize=1)
@EqualsAndHashCode(of="tno")
public class TimeTable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="t_seq")
	private Long tno;//시간표 번호
	
	private String title;//시간표 제목
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="gno")
	private GroupEntity group;//그룹과의 관계
	public void setGroup (GroupEntity group) {
		this.group = group;
	}
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="mno")
	private MemberEntity member;//사용자와의 관계
	public void setMember(MemberEntity member) {
		this.member = member;	
	}
	
	public TimeTable modifyThisToTargetTimeTable(TimeTable targetTimeTable) {
		this.title = targetTimeTable.title;
		return this;
	}
}
