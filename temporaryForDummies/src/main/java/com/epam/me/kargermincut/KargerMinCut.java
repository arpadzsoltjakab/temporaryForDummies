package com.epam.me.kargermincut;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

class Edge {
	private static int idCounter = 0;
	
	private int id = idCounter++;
	private Vertex left;
	private Vertex right;
	
	public Edge(Vertex left, Vertex right){
		this.left = left;
		this.right = right;
	}
	
	public int getId() {
		return id;
	}

	public Vertex getLeft() {
		return left;
	}

	public Vertex getRight() {
		return right;
	}

	public void changeVertex(Vertex from, Vertex to) {
		if (left == from) 
			left = to;
		
		if (right == from)
			right = to;
	}
}

class Vertex {
	private int id;
	private Set<Edge> edges = new HashSet<>();
	
	public Vertex(int id){
		this.id = id;
	}
	
	public void add(Edge edge) {
		edges.add(edge);
	}
	
	public void delete(Edge edge) {
		if (edges.contains(edge)) {
			edges.remove(edge);
		}
	}

	public int getId() {
		return id;
	}

	public void remove(Edge edge) {
		edges.remove(edge);
	}

	public Set<Edge> getEdges() {
		return edges;
	}

	public void add(Set<Edge> newEdges) {
		edges.addAll(newEdges);	
	}

	public void clearEdges() {
		edges.clear();
		
	}

	public boolean notConnectedTo(Vertex right) {
		for (Edge e : edges) {
			if (e.getLeft() == right || e.getRight() == right) {
				return false;
			}
		}
		return true;
	}
}

class Graph {
	private Map<Integer,Vertex> vertices = new HashMap<>();
	private List<Edge> edges = new LinkedList<>();
	private Random random = new Random();
	
	public void addVertex(Vertex vertex) {
		vertices.put(vertex.getId(), vertex);
	}
	
	public void addEdge(int leftId, int rightId) {
		Vertex left = vertices.get(leftId);
		Vertex right = vertices.get(rightId);
		
		if (left.notConnectedTo(right)) {
			Edge edge = new Edge(left, right);
			
			left.add(edge);
			right.add(edge);
			edges.add(edge);	
		}
	}
	
	public void removeRandomEdge() {
		int index = random.nextInt(edges.size());
		Edge edge = edges.remove(index);
		
		Vertex left = edge.getLeft();
		Vertex right = edge.getRight();
		
		Set<Edge> rightEdges = right.getEdges();
		
		for(Edge e : rightEdges) {
			e.changeVertex(right, left);
		}		
		left.add(rightEdges);
		
		
		for(Edge e : rightEdges) {
			if (e.getLeft() == e.getRight()) {
				edges.remove(e);
				left.remove(e);
			}
		}
		right.clearEdges();
		
		vertices.remove(right.getId());
	}

	public int numberOfVertices() {
		return vertices.size();
	}

	public int numberOfEdges() {
		return edges.size();
	}
}

public class KargerMinCut {
	private static Scanner in;  

	private static int minCut = 200;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		int initNumberOfVerticies = 200;
		
		for (int k = 0; k < 200000; k++) {
			FileReader inputStream  = new FileReader("/myjavaprograms/kargerMinCut.txt");
			in = new Scanner(inputStream);
			Graph graph = new Graph();
			for(int i = 1; i <= initNumberOfVerticies; i++){			  		
				Vertex vertex = new Vertex(i);
				graph.addVertex(vertex);
			}
			
			for (int i = 0; i < 200; i++) {
				String[] lineText = in.nextLine().split("\\s+");
				int[] lineNumbers = new int[lineText.length];
				for (int j = 0; j < lineNumbers.length; j++) {
					lineNumbers[j] = Integer.parseInt(lineText[j]);
					if (j != 0) {
						graph.addEdge(lineNumbers[0], lineNumbers[j]);
					}
				}
			}
			
			while (2 < graph.numberOfVertices() ) {
			  graph.removeRandomEdge();
			}
			
			if (minCut > graph.numberOfEdges() ) {
				minCut = graph.numberOfEdges();
			}	
			if (k % 1000 == 0) {
				System.out.println(k);
				System.out.println(minCut);
			}			
		}

		
		System.out.println(minCut);
	}
}
