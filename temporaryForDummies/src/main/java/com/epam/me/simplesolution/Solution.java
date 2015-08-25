package com.epam.me.simplesolution;

import java.util.*;

public class Solution {
	
	private static Scanner in = new Scanner(System.in); 
	
	public static void main(String[] args) {
    	int countOfTestCases = in.nextInt();
		
    	for(int i = 0; i < countOfTestCases; i++){			  		
    		runTestCase();
		}
    }
	private static void runTestCase() {		
		int sizeOfArray = in.nextInt();
		long modulus = in.nextLong();
		long[] numbers = new long[sizeOfArray];

		for (int i = 0; i < numbers.length; i++) {
			if(i == 0){
				numbers[i] = in.nextLong() % modulus;
			}
			else{
				numbers[i] = in.nextLong() + numbers[i-1] % modulus;	
			}
		}

		TreeSet<Long> numbersInOrder = new TreeSet<Long>();
		long maxSum = 0;
		for (long value : numbers) {
			if (numbersInOrder.isEmpty()) {
				maxSum = value;
				numbersInOrder.add(value);
			}
			else {
				maxSum = Math.max(value, maxSum);
				Long nextValue = numbersInOrder.ceiling(value + 1);
				if (nextValue != null) {
					maxSum = Math.max(value - nextValue + modulus, maxSum);
				}
				numbersInOrder.add(value);
			}
		}	
		System.out.println(maxSum);
	}
}	
