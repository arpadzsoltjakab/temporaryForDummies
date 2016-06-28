package com.epam.me.quicksort;

import java.util.Arrays;
import java.util.Scanner;

public class Quicksort {

	private static long counter = 0;
	
    public static void main(String[] args) {
       Scanner in = new Scanner(System.in);
       
       int lenght = in.nextInt();
       int[] array = new int[lenght];
       
       Arrays.setAll(array, i -> in.nextInt());
       
       quickSort(array, 0, lenght-1);  
       
      System.out.println(Arrays.toString(array));
      System.out.println(counter);
    }

	private static void quickSort(int[] array, int l, int r) {
		if (l < r) {	
			counter += r-l;
			
			//swap(array, l, r);
			swapMiddleElementWithFirst(array, l, r);

			int p = array[l];
			int i = l+1;
			
			for (int j = l+1; j <= r; j++) {
				if (array[j] < p) {
					swap(array, i, j);
					i++;
				}
			}
			swap(array, l, i - 1);

			quickSort(array, l, i - 2);
			quickSort(array, i, r);
		}
	}

	private static void swapMiddleElementWithFirst(int[] array, int l, int r) {
		for (int j = l; j <= r; j++) {
			System.out.print(array[j] + "");
		}
		System.out.println();
		int m = (l + r) / 2;
		int[] threeKings = {array[l],array[m], array[r]};
		Arrays.sort(threeKings);
	    
		if (array[m] == threeKings[1])
			swap(array, l, m);
		if (array[r] == threeKings[1])
			swap(array, l, r);
		
		for (int j = l; j <= r; j++) {
			System.out.print(array[j] + "");
		}
		System.out.println();
	}

	private static void swap(int[] array, int i, int j) {
		int tmp = array[j];
		array[j] = array[i];
		array[i] = tmp;
	}    
    
}
