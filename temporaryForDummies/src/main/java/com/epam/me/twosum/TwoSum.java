package com.epam.me.twosum;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;


public class TwoSum {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		InputStream inputStream  = ClassLoader.getSystemClassLoader().getResourceAsStream("twosum/2sum100000.txt");
		Scanner in = new Scanner(inputStream);
		
		HashSet<Long> numbers = new HashSet<>();

		while (in.hasNextLong()) {
			numbers.add(in.nextLong());
		} 
		
		int counter = 0;
		
		for (int i = -10000; i <= 10000; i++) {
			System.out.println(i);
			for (Long key : numbers) {
				if (key != i - key && numbers.contains(i-key)) {
					counter++;
					break;
				}
			}
		}
		System.out.println(counter);
	}	
}
