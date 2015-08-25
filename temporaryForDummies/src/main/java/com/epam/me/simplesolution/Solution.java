package com.epam.me.simplesolution;

import java.util.*;

public class Solution {
	
	private static Scanner in = new Scanner(System.in); 
	
	public static void main(String[] args) {
    	int countOfTestCases = in.nextInt();
		
    	for(int i = 0; i < countOfTestCases; i++){			  		
    		runTestCase();
		}
    }
	private static void runTestCase() {		
		int rows = in.nextInt();
		int cols = in.nextInt();
		
		char[][] forest = new char[rows][];
		
		for (int i = 0; i < rows; i++) {
			forest[i] = in.next().toCharArray();
		}
		
		int expectedWaves = in.nextInt();
		
		WaveCounter counter = new WaveCounter(forest);
		
		int count = counter.getWaveCount();
		
		if (count == expectedWaves) {
			System.out.println("Impressed");
		}
		else {
			System.out.println("Oops!");
		}
	}
	
	private static class WaveCounter {
		
		char[][] forest;
		
		int[][] visitedFields; 
		
		Field end;
		
		Field posOfHermione;
		
		Field nextField;
		
		LinkedList<Field> possableNextFields = new LinkedList<Field>();
		
		int waveCounter;
		
		int fieldCount;
		
		public WaveCounter(char[][] forest) {
			this.forest = forest;
			this.visitedFields = new int[forest.length][forest[0].length];
		}

		public int getWaveCount() {
			setStartAndEndField();
			
			return getWaveCountFromStartToEnd();
		}

		private int getWaveCountFromStartToEnd() {
			while(posOfHermione.notEquals(end)){
				makeAStep();
			}
			return waveCounter;
		}

		private void makeAStep() {
			calculatePossableNextField();
			calculateNextField();
			performStep();
		}

		private void performStep() {
			maintainWaveCounter();
			moveHermione();	
		}

		private void moveHermione() {
			visitedFields[posOfHermione.getRow()][posOfHermione.getCol()] = ++fieldCount;
			posOfHermione = nextField;
			possableNextFields.clear();
		}

		private void maintainWaveCounter() {
			if(isIntersection()) {
				if(isActFieldNotVisited()) {
					waveCounter++;
				}
				else if(isActVisitedOnce()) {
					waveCounter--;
				}
			}
		}

		private boolean isActVisitedOnce() {
			return (visitedFields[posOfHermione.getRow()][posOfHermione.getCol()] != 0 
					&& visitedFields[nextField.getRow()][nextField.getCol()] != 0
					&& possableNextFields.size() > 2 );
		}

		private boolean isActFieldNotVisited() {
			int notTraveledStepCounter = 0;
			ListIterator<Field> iterator = (ListIterator<Field>) possableNextFields.iterator();
			while (iterator.hasNext()) {
				Field field = iterator.next();
				if(visitedFields[field.getRow()][field.getCol()] == 0)
					notTraveledStepCounter++;
			}	
			return visitedFields[posOfHermione.getRow()][posOfHermione.getCol()] == 0 && notTraveledStepCounter > 1;
		}

		private boolean isIntersection() {
			return possableNextFields.size() > 1;
		}

		private void calculateNextField() {
			nextField = possableNextFields.getFirst();
			ListIterator<Field> iterator = (ListIterator<Field>) possableNextFields.iterator();
			while (iterator.hasNext()) {
				Field field = iterator.next();
				if(visitedFields[field.getRow()][field.getCol()] < visitedFields[nextField.getRow()][nextField.getCol()])
					nextField = field;
			}
		}

		private void calculatePossableNextField() {
			if(posOfHermione.getRow() > 0 && forest[posOfHermione.getRow()-1][posOfHermione.getCol()] != 'X')
				possableNextFields.add(new Field(posOfHermione.getRow()-1, posOfHermione.getCol()));
			if(posOfHermione.getRow() < forest.length -1 && forest[posOfHermione.getRow()+1][posOfHermione.getCol()] != 'X')
				possableNextFields.add(new Field(posOfHermione.getRow()+1, posOfHermione.getCol()));
			if(posOfHermione.getCol() > 0 && forest[posOfHermione.getRow()][posOfHermione.getCol()-1] != 'X')
				possableNextFields.add(new Field(posOfHermione.getRow(), posOfHermione.getCol()-1));
			if(posOfHermione.getCol() < forest[0].length -1 && forest[posOfHermione.getRow()][posOfHermione.getCol()+1] != 'X')
				possableNextFields.add(new Field(posOfHermione.getRow(), posOfHermione.getCol()+1));			
		}

		private void setStartAndEndField() {
			for (int i = 0; i < forest.length; i++) {
				for (int j = 0; j < forest[i].length; j++) {
					if (forest[i][j] == 'M') {
						posOfHermione= new Field(i, j);
								
					}
					else if (forest[i][j] == '*') {
						end = new Field(i, j);
					} 
				}				
			}
		}	
	} 
	
	private static class Field {
		
		int row;
		int col;
		
		public Field(int row, int col) {
			this.row = row;
			this.col = col;
		}

		public int getRow() {
			return row;
		}

		public void setRow(int row) {
			this.row = row;
		}

		public int getCol() {
			return col;
		}

		public void setCol(int col) {
			this.col = col;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + col;
			result = prime * result + row;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Field other = (Field) obj;
			if (col != other.col)
				return false;
			if (row != other.row)
				return false;
			return true;
		}
		
		public boolean notEquals(Object obj) {
			return !equals(obj);
		}		
	}
}	
