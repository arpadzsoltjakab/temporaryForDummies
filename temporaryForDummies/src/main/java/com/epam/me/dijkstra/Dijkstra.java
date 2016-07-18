package com.epam.me.dijkstra;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import java.util.Scanner;
import java.util.Set;


class Node {
	
	private int id;
	private boolean explored;
	
	private Set<Node> outEdges = new HashSet<>();
	
	public Node(int id){
		this.id = id;
	}
	
	public void addOutEdge(Node adjecent) {
		outEdges.add(adjecent);
	}
	
	public int getId() {
		return id;
	}

	public Set<Node> getOutEdges() {
		return outEdges;
	}
	
	public boolean isExplored() {
		return explored;
	}

	public void setExplored(boolean explored) {
		this.explored = explored;
	}
}

class Graph {
	private Map<Integer,Node> nodes = new HashMap<>();
	
	public void addNode(Node node) {
		nodes.put(node.getId(), node);
	}

	public Map<Integer, Node> getNodes() {
		return nodes;
	}

	public void addEdge(int first, int second) {
		Node firstNode = nodes.get(first);
		Node secondNode = nodes.get(second);
		
		firstNode.addOutEdge(secondNode);		
	}
}

public class Dijkstra {
	
	private static Scanner in;  
		
	public static void main(String[] args) throws FileNotFoundException {
		
		InputStream inputStream  = ClassLoader.getSystemClassLoader().getResourceAsStream("dijkstra/assignment.txt");
		in = new Scanner(inputStream);
		
		int initNumberOfVerticies = 200;
		
		Graph graph = new Graph();
		for(int i = 1; i <= initNumberOfVerticies; i++){			  		
			Node node = new Node(i);
			graph.addNode(node); 
		}
		
		for (int i = 0; i < initNumberOfVerticies; i++) {
			String[] lineText = in.nextLine().split("\\s+");
			int[] lineNumbers = new int[lineText.length];
			lineNumbers[0] = Integer.parseInt(lineText[0]); 
			System.out.print(lineText[0] + " ");
			for (int j = 1; j < lineNumbers.length; j++) {
				int vertex = Integer.parseInt(lineText[j].split(",")[0]);
				int length = Integer.parseInt(lineText[j].split(",")[1]);
				//lineNumbers[j] = Integer.parseInt(lineText[j]);
					//graph.addEdge(lineNumbers[0], lineNumbers[j]);
				System.out.print(lineNumbers[0] + "->" + length + "->" + vertex +" ");
			}
			System.out.println();
		}
					
		
	}
	
}
