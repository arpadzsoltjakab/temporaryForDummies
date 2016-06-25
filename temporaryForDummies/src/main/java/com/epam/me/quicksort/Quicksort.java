package com.epam.me.quicksort;

import java.util.Arrays;
import java.util.Scanner;

public class Quicksort {

	private static int counter = 0;
	
    public static void main(String[] args) {
       Scanner in = new Scanner(System.in);
       
       int lenght = in.nextInt();
       int[] array = new int[lenght];
       
       Arrays.setAll(array, i -> in.nextInt());
       
       quickSort(array, 0, lenght-1);  
       
      System.out.println(Arrays.toString(array));
      System.out.println(counter);
    }

	private static void quickSort(int[] array, int l,int r) {
		if (l < r) {	
			counter += r-l;
			int p = array[l];
			int i = l+1;
			
			for (int j = l+1; j <= r; j++) {
				if (array[j] < p) {
					swap(array, i, j);
					i++;
				}
			}
			swap(array, l, i-1);
			
			quickSort(array, l, i-1);
			quickSort(array, i, r);
		}
	}

	private static void swap(int[] array, int i, int j) {
		int tmp = array[j];
		array[j] = array[i];
		array[i] = tmp;
	}    
    
}
