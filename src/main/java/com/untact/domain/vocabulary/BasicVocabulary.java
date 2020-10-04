package com.untact.domain.vocabulary;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
@SequenceGenerator(name="bv_seq", initialValue=1, allocationSize=1)
@EqualsAndHashCode(of="bvno")
public class BasicVocabulary {
	/*
	 Vocabulary랑 상당히 유사함을 확인할 수 있다.
	 그러나 이것을 따로 분리한 이유는
	 basicvocabulary는 잘 변하지 않음에 비해
	 vocabulary는 사용자 입력에 따라 변할 가능성이 많기 때문이다.
	 그리고 basicvocabulary는 모든 사용자가 공통으로 이용하지만
	 vocabulary는 그룹마다 독립해서 사용하기 때문이다. 
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="bv_seq")
	private Long bvno;//기본 어휘 번호
	
	private String spelling;//기본 어휘 스펠링
	
	private String meaning;//기본 어휘 뜻
}
