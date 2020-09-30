package com.untact.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
@DiscriminatorValue(value="A2")
public class ExampleASpecial2 extends ExampleA {
	private String special2;
}
