package com.example.stringcleaner.impl;

import com.example.stringcleaner.base.StringProcessor;

public class StringReplacer extends StringProcessor {

	@Override
	protected String applyOnePass(String input) {
		if (input == null || input.length() < 3) {
			return input;
		}

		var sb = new StringBuilder();
		char prev = input.charAt(0);
		int count = 1;

		for (int i = 1; i <= input.length(); i++) {
			char current = i < input.length() ? input.charAt(i) : 0;

			if (i < input.length() && current == prev) {
				count++;
			} else {
				if (count >= 3) {
					char replacement = prev == 'a' ? '\0' : (char)(prev - 1);
					if (replacement != '\0') {
						sb.append(replacement);
					}
				} else {
					sb.append(String.valueOf(prev).repeat(count));
				}
				prev = current;
				count = 1;
			}
		}

		return sb.toString();
	}

}