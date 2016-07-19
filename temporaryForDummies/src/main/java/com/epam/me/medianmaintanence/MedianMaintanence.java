package com.epam.me.medianmaintanence;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

class MinHeap {
	private int size = 0;
	private int[] heap = new int[10000];
	
	public int min() {
		return heap[0];
	}
	
	public int extractMin() {
		int min = heap[0];
		heap[0] = heap[--size];
		
		int pos = 0;
		while (2*pos + 2 <= size && (heap[pos] > heap[2*pos + 1] || heap[pos] > heap[2*pos + 2])) {
			if (heap[2*pos + 1] <= heap[2*pos + 2]) {
				swap(pos, 2*pos + 1);
				pos = 2*pos + 1;
			}
			else if (2*pos + 2 < size) {
				swap(pos, 2*pos + 2);
				pos = 2*pos + 2;
			}
			else {
				break;
			}
		}		
		
		return min;
	}
	
	public void insert(int key) {
		int pos = size++;
		heap[pos] = key;
		
		while (pos != 0 && heap[pos] < heap[(pos-1)/2] ) {
			swap(pos, (pos-1)/2);
			pos = (pos-1)/2;
		}
	}

	private void swap(int pos1, int pos2) {
		int tmp = heap[pos1];
		heap[pos1] = heap[pos2];
		heap[pos2] = tmp;
	}
	
	public int getSize() {
		return size;
	}
}

class MaxHeap {
	private int size = 0;
	private int[] heap = new int[10000];
	
	public int max() {
		return heap[0];
	}
	
	public int extractMax() {
		int max = heap[0];
		heap[0] = heap[--size];
		
		int pos = 0;
		while (2*pos + 2 <= size && (heap[pos] < heap[2*pos + 1] || heap[pos] < heap[2*pos + 2])) {
			if (heap[2*pos + 1] >= heap[2*pos + 2]) {
				swap(pos, 2*pos + 1);
				pos = 2*pos + 1;
			}
			else if (2*pos + 2 < size) {
				swap(pos, 2*pos + 2);
				pos = 2*pos + 2;
			}
			else {
				break;
			}
		}		
		
		return max;
	}
	
	public void insert(int key) {
		int pos = size++;
		heap[pos] = key;
		
		while (pos != 0 && heap[pos] > heap[(pos-1)/2] ) {
			swap(pos, (pos-1)/2);
			pos = (pos-1)/2;
		}
	}

	private void swap(int pos1, int pos2) {
		int tmp = heap[pos1];
		heap[pos1] = heap[pos2];
		heap[pos2] = tmp;
	}
	
	public int getSize() {
		return size;
	}
}

public class MedianMaintanence {
	
	private MinHeap  heapHigh = new MinHeap();
	private MaxHeap  heapLow = new MaxHeap();

	public void add(int key) {
		if (key > heapHigh.min()) {
			heapHigh.insert(key);
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		InputStream inputStream  = ClassLoader.getSystemClassLoader().getResourceAsStream("medianmaintanence/median.txt");
		Scanner in = new Scanner(inputStream);

	}	
}
