package com.epam.me.simplesolution;

import java.util.*;

public class Solution {
	
	private static Scanner in = new Scanner(System.in); 
	
	public static void main(String[] args) {
    	int countOfTestCases = in.nextInt();
		
    	for(int i = 0; i < countOfTestCases; i++){			
    		int beginOfRange	= in.nextInt();
    		int endOfRange 		= in.nextInt();
    		System.out.println(runTestCase(beginOfRange, endOfRange));
		}
    }

	private static char[] runTestCase(int beginOfRange, int endOfRange) {
		// TODO Auto-generated method stub
		return null;
	}
}	