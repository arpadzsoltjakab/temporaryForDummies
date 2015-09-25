package com.epam.me.simplesolution;

import java.util.*;

import javax.xml.crypto.NodeSetData;

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

		Tree tree = new Tree();

		if (numberOfVertices % 2 == 0) {
			for (int i = 0; i < numberOfEdges; i++) {
				int child = in.nextInt();
				int parent = in.nextInt();
				tree.addNode(child);
				tree.addChildToNode(child, parent);
			}
			removedEdges = tree.countRemoveEdges();
		}
		System.out.println(removedEdges);
	}
}
class Tree {
	private HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();

	private int removedEdges = 0;
	
	public boolean containsNode(int node) {
		return nodes.containsKey(node);
	}

	public int countRemoveEdges() {

		for (Map.Entry<Integer, Node> nodeEntry : nodes.entrySet()) {
			nodeEntry.getValue().countRemoveEdges();
		}
		return removedEdges;
	}

	public void addNode(int value) {
		nodes.put(value, new Node(this, value));
	} 

	public void addChildToNode(int child,int node) {
		if (nodes.containsKey(node)) {
			Node parent = nodes.get(node);
			parent.addChild(child);
		}
		else {
			nodes.put(node, new Node(this, node, child));
		}
	} 		

	public HashSet<Integer> getChildrenOfNode(int node) {
		return nodes.get(node).getChildren();
	} 			


	private static class Node {

		int value;

		Tree tree;
		
		int numberOfChildren = 0;

		boolean isNotVisited = true;

		HashSet<Integer> children = new HashSet<Integer>();



		public int countRemoveEdges() {
			if (isNotVisited) {
				isNotVisited = false;
				for (Integer child : children) {
					int numberOfChildrenOfChild = tree.nodes.get(child).countRemoveEdges();
					if (numberOfChildrenOfChild > 0 && numberOfChildrenOfChild % 2 != 0) {
						tree.removedEdges++;
					}
					else{
						numberOfChildren += 1 + tree.nodes.get(child).countRemoveEdges();
					}
				}	
			} 
			return numberOfChildren;
		}
		
		public Node(Tree tree, int value) {
			this.tree = tree;
			this.value = value;
		}
		
		public Node(Tree tree, int value, int child) {
			this.value = value;
			this.tree = tree;
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

