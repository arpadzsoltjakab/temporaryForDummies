package com.epam.me.simplesolution;

import java.util.*;

public class Solution {
	
	private static Scanner in = new Scanner(System.in); 
	
	public static void main(String[] args) {
    	int countOfTestCases = 1;
    	for(int i = 0; i < countOfTestCases; i++){			  		
    		runTestCase();
		}
    }

	private static void runTestCase() {
		int numberOfVertices = in.nextInt();
		int numberOfEdges = in.nextInt();
		
		int removedEdges = 0;
		
		if (numberOfVertices % 2 == 0) {
			for (int i = 0; i < numberOfVertices; i++) {
				
			}
		}
	}
	
	private static class Tree {
		HashSet<Node> nodes = new HashSet<Node>();
	}
	
	private static class Node {
		
		int value;
		
		HashSet<Integer> children = new HashSet<Integer>();

		public Node(int value) {
			this.value = value;
		}	
		
		public Node(int value, int child) {
			this.value = value;
			children.add(child);
		}			
		
		public void addChild(int child) {
			children.add(child);
		}
		
		public HashSet<Integer> getChildren() {
			return children;
		}
	}

}	
