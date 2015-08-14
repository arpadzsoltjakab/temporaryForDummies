package com.epam.me.simplesolution;

import java.util.*;

public class Solution {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		Integer[] spam = new Integer[] { 1, 2, 3, 4, 5 };
		ArrayList<Integer> list  = new ArrayList<>(Arrays.asList(spam));
		System.out.println(reverse(list));
	}

	public static List<Integer> reverse (List<Integer> nums) {
		if(nums.size() > 1) {
			Integer tmp = nums.get(nums.size()-1);
			nums.set(nums.size()-1, nums.get(0));
			nums.set(0, tmp);
			List<Integer> subList = nums.subList(1, nums.size()-1);
			reverse(subList);
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