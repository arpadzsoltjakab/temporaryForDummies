package com.epam.me.bisector;

import java.util.*;

public class Bisector {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int[] input = new int[] { 1, 2, 3, 4, 10 };
		System.out.println(getIndexOfHalfSum(input));
	}


	public static int getIndexOfHalfSum(int[] input) {
		int allSum = 0;
		for(int i = 0; i <input.length; i++) {
			allSum += input[i];
		}
		
		int leftSum = 0, min = allSum, minIndex = 0;
		
		for (int i =0; i < input.length; i++) {
			leftSum += input[i];
			
			int actDif = Math.abs(allSum -(  2 * leftSum ));
			if (min > actDif) {
				min = actDif;
				minIndex = i;
			}		
		}	
		return minIndex;
	}
}	