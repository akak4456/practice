package com.untact.domain.reply;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@SequenceGenerator(name="r_seq", initialValue=1, allocationSize=1)
@EqualsAndHashCode(of="rno")
public class Reply {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="r_seq")
	private Long rno;//댓글 번호
	
	private String message;//댓글 내용
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@CreationTimestamp
	private LocalDateTime regdate;//댓글 생성 시간
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@UpdateTimestamp
	private LocalDateTime updatedate;//댓글 수정 시간
	
	@ManyToOne
	@JoinColumn(name="gno")
	private GroupEntity group;//이 답글은 어느 그룹에 속하는가?
	public void setGroup(GroupEntity group) {
		this.group = group;
	}
	
	@ManyToOne
	@JoinColumn(name="mno")
	private MemberEntity member;//이 답글은 어떤 사용자가 썻는가?
	public void setMember(MemberEntity member) {
		this.member = member;	
	}
	
	@ManyToOne
	@JoinColumn(name="bno")
	private Board board;//어느 board에 속하는가?
	public void setBoard(Board board) {
		this.board = board;
	}
	
	public Reply modifyThisToTargetReply(Reply targetReply) {
		this.message=targetReply.message;
		return this;
	}
}
