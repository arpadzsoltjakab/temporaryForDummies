package com.epam.me.kargermincut;

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
}

class Graph {
	private Map<Integer,Vertex> vertices = new HashMap<>();
	private List<Edge> edges = new LinkedList<>();
	private Random random = new Random();
	
	public void addVertex(Vertex vertex) {
		vertices.put(vertex.getId(), vertex);
	}
	
	public void removeVertex(Vertex vertex) {
		vertices.remove(vertex.getId());
	}
	
	public void addEdge(int leftId, int rightId) {
		Vertex left = vertices.get(leftId);
		Vertex right = vertices.get(rightId);
		Edge edge = new Edge(left, right);
		
		left.add(edge);
		right.add(edge);
		edges.add(edge);
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
		right.clearEdges();
		
		for(Edge e : edges) {
			if (e.getLeft() == e.getRight()) {
				edges.remove(e);
				left.remove(e);
			}
		}
		
		vertices.remove(right.getId());
	}
}

public class KargerMinCut {
	private static Scanner in = new Scanner(System.in); 

	public static void main(String[] args) {
		int initNumberOfVerticies = 200;
		
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
	}
}
