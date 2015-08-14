package com.epam.me.reverselist;

import java.util.*;

public class Reverse {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		Integer[] spam = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
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
}	