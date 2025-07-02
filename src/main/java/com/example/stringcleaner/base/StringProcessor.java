package com.example.stringcleaner.base;

import java.util.Optional;
import java.util.stream.Stream;

public abstract class StringProcessor {
	public String process(String input) {
		//use java 8+ feature if possible(requirement), and meanwhile old fashion way is not bad
		return Stream.iterate(
						Optional.ofNullable(input).orElse(""),
						s -> !s.equals(applyOnePass(s)),
						this::applyOnePass
				)
				.map(this::applyOnePass)
				.reduce((first, last) -> last)
				.orElse(input);
	}

	protected abstract String applyOnePass(String input);
}
