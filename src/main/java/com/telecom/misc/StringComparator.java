package com.telecom.misc;

import java.util.Locale;

import org.apache.commons.text.similarity.FuzzyScore;

public class StringComparator {
	
	/**
	 * matches guess against answer
	 */
	public boolean match(String answer, String guess) {
		answer = answer.split("-|\\(|,")[0]; // strip answer from useless details
		float normalization =  Math.max(answer.length(), guess.length()) * answer.split(" ").length;
		System.err.println(answer.split(" ").length);
		FuzzyScore score = new FuzzyScore(Locale.ENGLISH);
		System.err.println((float) score.fuzzyScore(answer,guess) / normalization);
		return ((float) score.fuzzyScore(answer,guess) / normalization) > 0.8;
	}
}
