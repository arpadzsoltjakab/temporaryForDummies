package com.epam.me.scc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

class Node {
	
	private int id;
	private int finishTime;
	private boolean explored;
	private Node leader;
	
	private Set<Node> outEdges = new HashSet<>();
	private Set<Node> inEdges = new HashSet<>();
	
	public Node(int id){
		this.id = id;
	}
	
	public void addOutEdge(Node adjecent) {
		outEdges.add(adjecent);
	}
	
	public void addInEdge(Node adjecent) {
		inEdges.add(adjecent);
	}

	public int getId() {
		return id;
	}

	public Set<Node> getOutEdges() {
		return outEdges;
	}
	
	public Set<Node> getInEdges() {
		return inEdges;
	}
	
	public boolean isExplored() {
		return explored;
	}

	public void setExplored(boolean explored) {
		this.explored = explored;
	}

	public void setLeader(Node leader) {
		this.leader = leader;
	}

	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;	
	}

	public Integer getFinishTime() {
		return finishTime;
	}
}

class Graph {
	private Map<Integer,Node> revMap = new HashMap<>();
	private Map<Integer,Node> nodeMap = new HashMap<>();
	
	public void addNodeToRevMap(Node node) {
		revMap.put(node.getId(), node);
	}
	
	public void addNodeToNodeMap(Node node) {
		nodeMap.put(node.getFinishTime(), node);
	}

	public Map<Integer, Node> getRevMap() {
		return revMap;
	}

	public Map<Integer, Node> getNodeMap() {
		return nodeMap;
	}

	public void addEdge(int first, int second) {
		Node firstNode = revMap.get(first);
		Node secondNode = revMap.get(second);
		
		firstNode.addOutEdge(secondNode);
		secondNode.addInEdge(firstNode);
		
	}
}

public class Scc {
	
	private static Scanner in;  
	private static int initNumberOfVerticies = 875714;
	//private static int initNumberOfVerticies = 9;
	private static int t = 0;
	private static int numberOfConnections = 0;
	private static Node s = null;
	private static List<Integer> sccSet = new LinkedList<Integer>();
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		InputStream inputStream  = ClassLoader.getSystemClassLoader().getResourceAsStream("scc/scc.txt");
		in = new Scanner(inputStream);
		
		Graph graph = new Graph();
		for(int i = 1; i <= initNumberOfVerticies; i++){			  		
			Node node = new Node(i);
			graph.addNodeToRevMap(node);
		}
		
		while (in.hasNext()) {
			int first = in.nextInt();
			int second = in.nextInt();
			graph.addEdge(first, second);
		}
		
//		for (int i = 0; i < 11; i++) {
//			int first = in.nextInt();
//			int second = in.nextInt();
//			graph.addEdge(first, second);			
//		}
		
		for(int i = initNumberOfVerticies ; i > 0; i--){	
			Node actNode = graph.getRevMap().get(i);
			if (!actNode.isExplored()) {
				revDepthFirstSearch(graph, actNode);
			}
		}
		
		for(int i = initNumberOfVerticies ; i > 0; i--){	
			Node actNode = graph.getRevMap().get(i);
			actNode.setExplored(false);
		}
		
		for(int i = initNumberOfVerticies ; i > 0; i--){	
			Node actNode = graph.getNodeMap().get(i);
			if (!actNode.isExplored()) {
				s = actNode;
				numberOfConnections = 0;
				depthFirstSearch(graph, actNode);
				sccSet.add(numberOfConnections);
				//System.out.println("leader" + s.getId());
				//System.out.println("id" + numberOfConnections);
			}
		}
		
		for (Integer i : sccSet) {
			System.out.println(i);
		}
	}
	
	private static void depthFirstSearch(Graph graph, Node actNode) {
		actNode.setExplored(true);
		actNode.setLeader(s);
		numberOfConnections++;
		for (Node node : actNode.getOutEdges()) {
			if (!node.isExplored()) {
				depthFirstSearch(graph, node);
			}
		}		
	}

	private static void revDepthFirstSearch(Graph graph, Node actNode) {
		actNode.setExplored(true);
		for (Node node : actNode.getInEdges()) {
			if (!node.isExplored()) {
				revDepthFirstSearch(graph, node);
			}
		}
		actNode.setFinishTime(++t);
		graph.addNodeToNodeMap(actNode);
	}
}
