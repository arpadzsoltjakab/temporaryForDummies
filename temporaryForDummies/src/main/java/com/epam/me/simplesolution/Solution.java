package com.epam.me.simplesolution;

import java.util.*;

public class Solution {
	
	private static Scanner in = new Scanner(System.in); 
	
	public static void main(String[] args) {
    	int countOfTestCases = 1;
		
    	for(int i = 0; i < countOfTestCases; i++){			  		
    		runTestCase();
		}
    }
	private static void runTestCase() {		
		int sizeOfListA = in.nextInt();
		
		HashMap<Integer, Integer> missingHash = new HashMap<Integer, Integer>();
		
		
		Integer value;
		for (int i = 0; i < sizeOfListA; i++) {
			int key = in.nextInt();
			if(missingHash.containsKey(key)) {
				value = missingHash.get(key);
				missingHash.put(key, ++value);
			}
			else {
				missingHash.put(key, 1);
			}
		}
		
		int sizeOfListB = in.nextInt();
		Set<Integer> missingNumbers = new TreeSet<Integer>();
		for (int i = 0; i < sizeOfListB; i++) {
			int key = in.nextInt();
			if((value = missingHash.get(key)) != null) {
				missingHash.put(key, --value);
			}
			if (value == null || value == -1) {
				missingNumbers.add(key);
			}
		}	
		
		Iterator iterator = missingNumbers.iterator();

		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
	}
}	
