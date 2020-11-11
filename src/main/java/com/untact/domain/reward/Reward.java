package com.untact.domain.reward;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table
@SequenceGenerator(name="rw_seq", initialValue=1, allocationSize=1)
@EqualsAndHashCode(of="rwno")
public class Reward {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="rw_seq")
	private Long rwno;//상금 번호
	
	private Long rewardAmount;//상금 액수
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@CreationTimestamp
	private LocalDateTime regdate;//생성 시간
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@UpdateTimestamp
	private LocalDateTime updatedate;//수정 시간
	
	@ManyToOne
	@JoinColumn(name="gno")
	private GroupEntity group;//그룹 관계
	public void setGroup(GroupEntity group) {
		this.group = group;
	}
	
	@ManyToOne
	@JoinColumn(name="mno")
	private MemberEntity member;//멤버 관계
	public void setMember(MemberEntity member) {
		this.member = member;
	}
	
	public Reward changeRewardAmount(Long newAmount) {
		this.rewardAmount = newAmount;
		return this;
	}
}
