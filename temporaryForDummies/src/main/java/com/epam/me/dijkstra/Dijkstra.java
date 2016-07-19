package com.epam.me.dijkstra;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

class Edge {
	private Node start;
	private Node end;
	private int length;
	
	public Edge(Node start, Node end, int length) {
		this.start = start;
		this.end = end;
		this.length = length;
	}

	public Node getStart() {
		return start;
	}	
	
	public Node getEnd() {
		return end;
	}

	public int getLength() {
		return length;
	}	
	
	
}

class Node {
	
	private int id;
	private int distance;
	private Node prev;
	
	private Set<Edge> edges = new HashSet<>();
	
	public Node(int id){
		this.id = id;
	}
	
	public void addEdge(Edge edge) {
		edges.add(edge);
	}
	
	public int getId() {
		return id;
	}

	public Set<Edge> getOutEdges() {
		return edges.stream().filter(edge -> edge.getStart() == this).collect(Collectors.toSet());
	}
	
	public Set<Edge> getInEdges() {
		return edges.stream().filter(edge -> edge.getEnd() == this).collect(Collectors.toSet());
	}
	
	
	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getDistance() {
		return distance;
	}
	
	public Node getPrev() {
		return prev;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}

}

class Graph {
	private Map<Integer,Node> nodes = new HashMap<>();
	private Set<Edge> edges = new HashSet<>();
	
	public void addNode(Node node) {
		nodes.put(node.getId(), node);
	}

	public Node getNode(int id) {
		return nodes.get(id);
	}
	
	public Map<Integer, Node> getNodes() {
		return nodes;
	}

	public void addEdge(Edge edge) {
		edges.add(edge);
	}
	
	public Set<Edge> getEdges() {
		return edges;
	}
}

public class Dijkstra {
	
	private static final int ZERO = 0;
	private static final int SOURCE_ID = 1;
	private static Scanner in;  
		
	public static void main(String[] args) throws FileNotFoundException {
		
		InputStream inputStream  = ClassLoader.getSystemClassLoader().getResourceAsStream("dijkstra/assignment.txt");
		in = new Scanner(inputStream);
		
		int initNumberOfVerticies = 200;
		Set<Node> Q = new HashSet<>();
		
		
		Graph graph = new Graph();
		for(int i = 1; i <= initNumberOfVerticies; i++){			  		
			Node node = new Node(i);
			node.setDistance(10000);
			graph.addNode(node); 
			Q.add(node);
		}

		
		for (int i = 0; i < initNumberOfVerticies; i++) {
			String[] lineText = in.nextLine().split("\\s+");

			int startId = Integer.parseInt(lineText[0]); 

			for (int j = 1; j < lineText.length; j++) {
				int endId = Integer.parseInt(lineText[j].split(",")[0]);
				int length = Integer.parseInt(lineText[j].split(",")[1]);
				
				Node startNode = graph.getNode(startId);
				Node endNode = graph.getNode(endId);
				
				Edge edge = new Edge(startNode, endNode, length);
				graph.addEdge(edge);
				startNode.addEdge(edge);
				endNode.addEdge(edge);
				
			}
		}
		
		graph.getNode(SOURCE_ID).setDistance(ZERO);
		Comparator<Node> comp = (n1, n2) -> Integer.compare(n1.getDistance(), n2.getDistance());
		
		while (!Q.isEmpty()) {
			Node startNode = Q.stream().min(comp).get();
			Q.remove(startNode);
			for (Edge edge : startNode.getOutEdges()) {
				Node endNode = edge.getEnd();
				if (Q.contains(endNode)) {
					int newLength = startNode.getDistance() + edge.getLength();
					if (newLength < endNode.getDistance()) {
						endNode.setDistance(newLength);
						endNode.setPrev(startNode);
					}
				}
			}
		}

		
		List<Node> answer = new ArrayList<>();
		answer.add(graph.getNode(7));
		answer.add(graph.getNode(37));
		answer.add(graph.getNode(59));
		answer.add(graph.getNode(82));
		answer.add(graph.getNode(99));
		answer.add(graph.getNode(115));
		answer.add(graph.getNode(133));
		answer.add(graph.getNode(165));
		answer.add(graph.getNode(188));
		answer.add(graph.getNode(197));
		for (Node node : answer) {
			System.out.println("Node: " + node.getId() + " Distance: " + node.getDistance() + " Prev: " + (node.getPrev() == null ? 0 :node.getPrev().getId()));
		}
				
	}
	
}
