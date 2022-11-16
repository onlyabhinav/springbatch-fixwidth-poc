package com.onlyabhinav.batchfixwidth.pattern;

import java.util.regex.Pattern;

public class TestPattern {

	public static void main(String[] args) {

		String[] s1={
				"HSHKXDO234232",
				"HSHKYDO234232",
				"HSsHKXDO234232"
				};
		
		
		for (String s:s1) {
			boolean r = Pattern.matches("^.{4}X.*", s);
			System.out.println(s + " :Contains X at 5th position = " + r);
		}

	}

}
