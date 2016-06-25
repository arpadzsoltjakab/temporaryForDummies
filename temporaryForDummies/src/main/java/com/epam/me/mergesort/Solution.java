package com.epam.me.mergesort;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	
    public static long countInversions(int[] numbers) {      
		if (numbers.length == 1) 
			return 0;

		int[] left = Arrays.copyOfRange(numbers, 0, numbers.length/2);  
		int[] right = Arrays.copyOfRange(numbers, numbers.length/2, numbers.length); 
		
		long leftInversions = countInversions(left);
		long rightInversions = countInversions(right);
		long splitInversions = countSplit(numbers, left, right);
		
		return leftInversions + rightInversions + splitInversions;
    }  
      
    private static long countSplit(int[] numbers, int[] left, int[] right) {
    	int i = 0;
    	int j = 0;
    	long count = 0;
    	for (int k = 0; k < numbers.length; k++) {
    		if (i == left.length ) {
				numbers[k] = right[j];
				j++;	
			}
    		else if (j == right.length ) {
				numbers[k] = left[i];
				i++;	
			}
    		else if (left[i] <= right[j]) {
				numbers[k] = left[i];
				i++;
			}
			else {
				numbers[k] = right[j];
				count += left.length - i;
				j++;	
			}
    		
		}
    	return count;
	}

	public static void main(String[] args) {
       Scanner in = new Scanner(System.in);
       int number_of_elements = in.nextInt();
       
       int[] numbers = new int[number_of_elements];
       
       for(int i=0;i<number_of_elements;i++){
            numbers[i] = in.nextInt(); 
       }
       
       System.out.println(countInversions(numbers));                       
    }    
	
}