package com.untact.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="DTYPE")
@SequenceGenerator(name="a_seq", initialValue=1, allocationSize=1)
@EqualsAndHashCode(of="ano")
public abstract class ExampleA {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="a_seq")
	private Long ano;
	
	private String common;
}
