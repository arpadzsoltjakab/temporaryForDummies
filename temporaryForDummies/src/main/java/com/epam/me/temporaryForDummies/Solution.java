package com.epam.me.temporaryForDummies;

import java.util.*;

public class Solution {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
//		int t = in.nextInt();
		Integer[] spam = new Integer[] { 1, 2, 3, 4, 5 };
		ArrayList<Integer> list  = new ArrayList<>(Arrays.asList(spam));

//		for(int i=0;i<t;i++){
//			list.add(in.nextInt());
//		}
		System.out.println(reverse(list));
	}

	public static List<Integer> reverse (List<Integer> nums) {
		List<Integer> reverseList = new ArrayList<Integer>(Arrays.asList(nums.remove(nums.size()-1)));
		if(nums.size() > 0) {
			reverseList.addAll(reverse(new ArrayList<Integer>(nums.subList(0, nums.size()))));
		}
		return reverseList;
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