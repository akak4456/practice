package com.untact.domain.board;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import com.untact.domain.file.FileEntity;
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
@SequenceGenerator(name="b_seq", initialValue=1, allocationSize=1)
@EqualsAndHashCode(of="bno")
public class Board {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="b_seq")
	private Long bno;//게시판 글 번호
	
	private String title;//게시판 글 제목
	
	
	@Column(columnDefinition = "LONGTEXT")
	private String content;//게시판 글 내용
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private BoardKind kind;//게시판 글 종류(일반 글인지 질의 응답인지)
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@CreationTimestamp
	private LocalDateTime regdate;//게시판 등록 시간
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@UpdateTimestamp
	private LocalDateTime updatedate;//게시판 수정 시간
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="gno")
	private GroupEntity group;//어떤 그룹이 이 글을 썻는지 나타냄
	public void setGroup (GroupEntity group) {
		this.group = group;
	}
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="mno")
	private MemberEntity member;//어떤 사용자가 이 글을 썻는지 나타냄
	public void setMember(MemberEntity member) {
		this.member = member;	
	}
	
	public Board modifyThisToTargetBoard(Board targetBoard) {
		this.title = targetBoard.title;
		this.content = targetBoard.content;
		this.files.clear();
		this.files.addAll(targetBoard.files);
		return this;
	}
	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="bno")
	private List<FileEntity> files;
	public void setFiles(List<FileEntity> files) {
		this.files = files;
	}
	
}
