package com.epam.me.temporaryForDummies;

import java.util.*;

public class Interview {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		Integer[] spam = new Integer[] { 1, 2, 3, 4, 5 };
		ArrayList<Integer> list  = new ArrayList<>(Arrays.asList(spam));
		System.out.println(reverse(list));
	}

	public static List<Integer> reverse (List<Integer> nums) {
		if( nums == null ) {
			throw new IllegalArgumentException("The list cannot be null!");
		}
		if(nums.size() > 1) {
			List<Integer> trimmedList = nums.subList(1, nums.size()-1);
			reverse(trimmedList);
			Integer tmp = nums.get(nums.size()-1);
			nums.set(nums.size()-1, nums.get(0));
			nums.set(0, tmp);
		}
		return nums;
	}
	
	public static int getIndex(int[] input) {
		int allSum = 0;
		for(int i =0; i <input.length; i++) {
			allSum += input[i];
		}
		
		int leftSum = 0, rightSum = 0, min = allSum, minIndex = 0;
		
		for(int i =0; i <input.length; i++) {
			leftSum += input[i];
			if( min > Math.abs(allSum -(  2 * leftSum ) )) {
				min = Math.abs(leftSum-rightSum);
				minIndex = i;
			}
			
		}	
		return minIndex;
	}
}	