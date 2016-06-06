package com.epam.me.simplesolution;

import java.util.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int[] stockValues = new int[in.nextInt()];
        
        Arrays.setAll(stockValues, i -> in.nextInt());
               
        int loss = 0;
        int max = stockValues[0];


        for (int value : stockValues) {
        	if (value > max) {
        		max = value;
			}
        	else if (value - max < loss ) {
        		loss = value - max;
			}
		}       
        System.out.println(loss);
    }
}

