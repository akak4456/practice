package com.untact.domain.group;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import com.untact.domain.timetable.TimeTable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
@SequenceGenerator(name="g_seq", initialValue=1, allocationSize=1)
@EqualsAndHashCode(of="gno")
public class GroupEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="g_seq")
	private Long gno;//그룹 번호
	
	private String title;//그룹명
	
	private String type;//시험 종류
	
	private String detail;//그룹 설명
	
	private Long maximumNumberOfPeople;//최대 인원수
	
	private Long depositToBePaid;//내야 하는 예치금
	
	private Long maximumNumberOfAbsencesAllowed;//최대 결석 횟수
	
	private Long fineForBeingLate;//지각할 때 벌금
	
	private Long fineForBeingAbsence;//결석할 때 벌금
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime duedate;//그룹 기한(그룹이 언제 끝날 것인지)
	
	@Enumerated(EnumType.ORDINAL)
	@NotNull
	private WillRecruit willRecruit;//그룹이 사람들을 뽑을 것이냐?(Y,N으로 갈림)
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@CreationTimestamp
	private LocalDateTime regdate;//그룹 생성 시간
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@UpdateTimestamp
	private LocalDateTime updatedate;//그룹 수정 시간
	
	@OneToOne
	@JoinColumn(name="tno")
	@JsonIgnore
	private TimeTable representativeTimeTable;
	public void setTimeTable(TimeTable representativeTimeTable) {
		this.representativeTimeTable = representativeTimeTable;
	}
}
