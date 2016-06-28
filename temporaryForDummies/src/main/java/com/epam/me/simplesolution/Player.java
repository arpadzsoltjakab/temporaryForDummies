package com.epam.me.simplesolution;

import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

enum Direction {
	TOP, DOWN, LEFT, RIGHT;	
	
	public Direction opposite(){
		Direction opposite = this;
		
		switch (this) {
		case TOP:
			opposite = DOWN;
			break;
		case DOWN:
			opposite = TOP;
			break;			
		case LEFT:
			opposite = RIGHT;
			break;
		case RIGHT:
			opposite = LEFT;
			break;				
		default:
			break;
		}
		
		return opposite;
	}
}

enum Path {
	TOP_DOWN(Direction.TOP, Direction.DOWN), 
	LEFT_DOWN(Direction.LEFT, Direction.DOWN), 
	RIGHT_DOWN(Direction.RIGHT, Direction.DOWN), 
	LEFT_RIGHT(Direction.LEFT, Direction.RIGHT), 
	RIGHT_LEFT(Direction.RIGHT, Direction.LEFT), 
	TOP_LEFT(Direction.TOP, Direction.LEFT), 
	TOP_RIGHT(Direction.TOP, Direction.RIGHT);
	
	Path(Direction entrance, Direction exit){
		this.entrance = entrance;
		this.exit = exit;
	}
	
	private Direction entrance;
	private Direction exit;
	
	public Direction getEntrance() {
		return entrance;
	}
	
	public Direction getExit() {
		return exit;
	}
}

enum RoomType {	
	TYPE_0(),
	TYPE_1(Path.TOP_DOWN, Path.LEFT_DOWN, Path.RIGHT_DOWN),
	TYPE_2(Path.LEFT_RIGHT, Path.RIGHT_LEFT),
	TYPE_3(Path.TOP_DOWN),
	TYPE_4(Path.TOP_LEFT, Path.RIGHT_DOWN),
	TYPE_5(Path.TOP_RIGHT, Path.LEFT_DOWN),
	TYPE_6(Path.LEFT_RIGHT, Path.RIGHT_LEFT),
	TYPE_7(Path.TOP_DOWN, Path.RIGHT_DOWN),
	TYPE_8(Path.LEFT_DOWN, Path.RIGHT_DOWN),
	TYPE_9(Path.LEFT_DOWN, Path.TOP_DOWN),
	TYPE_10(Path.TOP_LEFT),
	TYPE_11(Path.TOP_RIGHT),
	TYPE_12(Path.RIGHT_DOWN),
	TYPE_13(Path.LEFT_DOWN);
	
	private RoomType(Path... paths) {
		for (Path path : paths) {
			this.paths.add(path);
		}
	}
	
	private Set<Path> paths = new HashSet<>();

	
	public RoomType rotateRight() {
		RoomType rotated = this;
		
		switch (this) {
		case TYPE_2:
			rotated = TYPE_3;
			break;
		case TYPE_3:
			rotated = TYPE_2;
			break;
		case TYPE_4:
			rotated = TYPE_5;
			break;	
		case TYPE_5:
			rotated = TYPE_4;
			break;	
		case TYPE_6:
			rotated = TYPE_7;
			break;	
		case TYPE_7:
			rotated = TYPE_8;
			break;		
		case TYPE_8:
			rotated = TYPE_9;
			break;		
		case TYPE_9:
			rotated = TYPE_6;
			break;		
		case TYPE_10:
			rotated = TYPE_11;
			break;		
		case TYPE_11:
			rotated = TYPE_12;
			break;		
		case TYPE_12:
			rotated = TYPE_13;
			break;		
		case TYPE_13:
			rotated = TYPE_10;
			break;		
		default:
			break;
		}
		
		return rotated;
	}
	
	public RoomType rotateLeft() {
		RoomType rotated = this;
		
		switch (this) {
		case TYPE_2:
			rotated = TYPE_3;
			break;
		case TYPE_3:
			rotated = TYPE_2;
			break;
		case TYPE_4:
			rotated = TYPE_5;
			break;	
		case TYPE_5:
			rotated = TYPE_4;
			break;	
		case TYPE_6:
			rotated = TYPE_9;
			break;	
		case TYPE_7:
			rotated = TYPE_6;
			break;		
		case TYPE_8:
			rotated = TYPE_7;
			break;		
		case TYPE_9:
			rotated = TYPE_8;
			break;		
		case TYPE_10:
			rotated = TYPE_13;
			break;		
		case TYPE_11:
			rotated = TYPE_10;
			break;		
		case TYPE_12:
			rotated = TYPE_11;
			break;	
		case TYPE_13:
			rotated = TYPE_12;
			break;	
		default:
			break;
		}
		
		return rotated;
	}
	
	public boolean hasExit(Direction exit) {
		return paths.stream().filter(path -> path.getExit() == exit).findFirst().isPresent();
	}	
	
	public boolean hasEntrance(Direction entrance) {
		return paths.stream().filter(path -> path.getEntrance() == entrance).findFirst().isPresent();
	}	
	
	public Direction getEntranceByExit(Direction exit) {
		Direction entrance = null;
		
		if (hasExit(exit)) {
			entrance = paths.stream().filter(path -> path.getExit() == exit).findFirst().get().getEntrance();
		}	 
		return entrance;
	}	
	
	public Direction getExitByEntrance(Direction entrance) {
		Direction exit = null;
		
		if (hasEntrance(entrance)) {
			exit = paths.stream().filter(path -> path.getEntrance() == entrance).findFirst().get().getExit();
		}	 
		return exit;
	}	
}

class Position {
	private int width;
	private int height;
	
	Position(int width, int height) {
		this.width = width;
		this.height = height;
	}

	boolean isLeftFrom(Position position) {
		boolean leftFromTheOther = false; 
		if (this.width < position.width && this.height == position.height) {
			leftFromTheOther = true;
		}
		return leftFromTheOther;
	}
	
	boolean isRightFrom(Position position) {
		boolean rightFromTheOther = false; 
		if (position.width < this.width && this.height == position.height) {
			rightFromTheOther = true;
		}
		return rightFromTheOther;
	}
	
	boolean isUpFrom(Position position) {
		boolean rightFromTheOther = false; 
		if (position.width == this.width && this.height < position.height) {
			rightFromTheOther = true;
		}
		return rightFromTheOther;
	}
	
	boolean isUnderFrom(Position position) {
		boolean rightFromTheOther = false; 
		if (position.width == this.width && position.height < this.height) {
			rightFromTheOther = true;
		}
		return rightFromTheOther;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Position goLeft() {
		return new Position(width-1, height);
	}
	
	public Position goRight() {
		return new Position(width+1, height);
	}
	
	public Position goDown() {
		return new Position(width, height+1);
	}
	
	public Position goUp() {
		return new Position(width, height-1);
	}
	
	@Override
	public String toString() {
		return width + " " + height;
	};
	
	@Override
	public boolean equals(Object otherObject) {

		if (this == otherObject) return true;
		
		if (otherObject == null) return false;
		
		if (getClass() != otherObject.getClass()) return false;
		
		Position other = (Position) otherObject;
		
		return Objects.equals(width, other.width) && height == other.height;
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(width, width);
	}

	public int distanceFrom(Position position) {
		return  Math.abs(this.width - position.width) + Math.abs(this.height - position.height);
	}
	
	public boolean isDangerousDistance(Position position) {
		return  position.width - this.width == position.height - this.height;
	}
}

class Room {
	
	private boolean rotatable;
	
	private Integer distance;
	
	private Grid grid;
	
	private RoomType defaultType;
	
	private RoomType actType;
	
	private Room prev;
	
	private Room next;
	
	private Position position;
	
	private Direction actEntrance = null;
	
	private Map<Room, HashSet<RoomType>> triedTypes = new HashMap<>();
 	
	public Room(RoomType type, Position position, boolean rotatable, Grid grid) {
		this.defaultType = type;
		this.actType = type;
		this.position = position;
		this.rotatable = rotatable;
		this.grid = grid;
		this.grid.setRoom(position, this);
	}

	public Position getPosition() {
		return position;
	}
	
	public RoomType getActType() {
		return actType;
	}

	public boolean isRotatable() {
		return rotatable;
	}

	public void setRotatable(boolean rotatable) {
		this.rotatable = rotatable;
	}

	public Direction getEntrance() {
		return actType.hasEntrance(actEntrance) ? actEntrance : null;
	}

	public void setEntrance(Direction entrance) {
		this.actEntrance = entrance;
	}

	public Direction getExit() {
		return actType.getExitByEntrance(getEntrance());
	}

	
	public void rotateLeft() {
		actType = actType.rotateLeft();
	}
	
	public void rotateRight() {
		actType = actType.rotateRight();
	}
	
	public Room getNextRoom() {
		Room nextRoom = null;
		System.err.println("entrance " + this.getEntrance());
		System.err.println("exit " + this.getExit());
		System.err.println("type " + this.defaultType);
		
		setActTypeForNextRoom();
		
		if ((nextRoom == null || nextRoom.isBlocker()) && !triedTypes.get(prev).contains(actType)) {
			triedTypes.get(prev).add(actType);
			nextRoom = getActRoomTypeExitsNext();
			
			if (nextRoom != null && nextRoom.isGateWay()) {
				nextRoom.actEntrance = getExit().opposite();
				nextRoom.prev = this;
				next = nextRoom;
			}	
			else {
				nextRoom = getNextRoom();
			}
		}	
		
		return nextRoom;
	}

	private void setActTypeForNextRoom() {
		if (!triedTypes.containsKey(prev)) {
			triedTypes.put(prev, new HashSet<RoomType>());
			actType = defaultType;
		}
		else if (rotatable) {
			rotateRight();
		}
	}

	private Room getActRoomTypeExitsNext() {
		Room nextRoom = null;
		Direction exit = getExit();
		if (exit != null) {
			if (exit == Direction.LEFT) {
				nextRoom = grid.getRoom(position.goLeft());
				System.err.println("LEFT");
			}
			else if (exit == Direction.RIGHT) {
				nextRoom = grid.getRoom(position.goRight());
				
				System.err.println("RIGHT");
			}
			else if (exit == Direction.DOWN) {
				nextRoom = grid.getRoom(position.goDown());
				
				System.err.println("DOWN");
			}
		}
		return nextRoom;
	}
	
	public void initializeRoom() {
		triedTypes.clear();
		actType = defaultType;
	}

	public boolean isGateWay() {
		return this.defaultType != RoomType.TYPE_0;
	}
	
	public boolean isBlocker() {
		return this.defaultType == RoomType.TYPE_0;
	}

	public boolean isNotGateWayFrom(Room room) {
		return  triedTypes.get(room) != null && triedTypes.get(room).size() == 4;
	}

	public Room getPrev() {
		return prev;
	}
	
	public Room getNext() {
		return next;
	}

	public int getRotation() {
		int rotation = 0;
		if (defaultType == actType) {
			rotation = 0;
		}
		else if (defaultType.rotateRight() == actType) {
			rotation = 1;
		}
		else if (defaultType.rotateLeft() == actType) {
			rotation = -1;
		}
		else {
			rotation = 2;
		}
		return rotation;
	}
	
}

class Grid {

	Map<Position, Room> rooms = new HashMap<>();
	
	Queue<String> rotationQueue = new LinkedList<String>();
	
	private Position endPosition;
	private Position startPosition;
	private int height;
	private int width;
	
	public Grid(int width, int height) {
		this.width = width;
		this.height = height; 
	}
	
	Room getRoom(Position position) {
		return rooms.get(position);
	}
	
	public void setStartPosition(Position startPosition) {
		this.startPosition = startPosition;
		this.getRoom(startPosition).setEntrance(Direction.TOP);
	}
	
	public void setEndPosition(Position endPosition) {
		this.endPosition = endPosition;
	}
	
	public void setRoom(Position position, Room room) {
		rooms.put(position, room);
	}

	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void findPathFromStartToEnd() {
		System.err.println("move " +  getRoom(startPosition).getPosition() );
		Room actRoom = getRoom(startPosition).getNextRoom();
		System.err.println("move " + actRoom.getPosition() );
		while (actRoom != getRoom(endPosition)) {
			
			Room nextRoom = actRoom.getNextRoom();
			if (nextRoom == null || nextRoom.isBlocker()) {
				actRoom = actRoom.getPrev();
			}
			else{
				actRoom = nextRoom;
			}
			System.err.println("move " + actRoom.getPosition() );
		}
		
		actRoom = getRoom(startPosition).getNext();
		
		while (actRoom != getRoom(endPosition)) {
			if (actRoom.isRotatable()) {
				if (actRoom.getRotation() == 1) {
					rotationQueue.add(actRoom.getPosition() + " RIGHT");
				}
				else if (actRoom.getRotation() == -1) {
					rotationQueue.add(actRoom.getPosition() + " LEFT");
				}
				else if (actRoom.getRotation() == 2) {
					rotationQueue.add(actRoom.getPosition() + " RIGHT");
					rotationQueue.add(actRoom.getPosition() + " RIGHT");
				}
			}
			actRoom = actRoom.getNext();
		}
	}

	public String nextCommand() {
		String nextCommand = "WAIT";
		if (!rotationQueue.isEmpty()) {
			nextCommand = rotationQueue.poll();
		}
		return nextCommand;
	}

	public boolean hasFreeWayFrom(Position indiActPosition) {
		boolean hasFreeWay = true;
		if (getRoom(indiActPosition).getNext().getRotation() != 0 || 
			getRoom(indiActPosition).getNext().getNext().getRotation() == 2) {
			hasFreeWay = false;
		}
		return hasFreeWay;
	}

}

class Player {

	private static final Scanner IN = new Scanner(System.in);
	
    public static void main(String args[]) {
        
        int width = IN.nextInt(); 
        int height = IN.nextInt(); 
        IN.nextLine();
        System.err.println(width + " " + height);
        
        Grid tunnelMap = new Grid(width, height);
        
        fillTunelMap(tunnelMap);
        
        int exit = IN.nextInt(); // the coordinate along the X axis of the exit (not useful for this first mission, but must be read).
        System.err.println(exit);
        tunnelMap.setEndPosition(new Position(exit, height - 1));
        
        tunnelMap.findPathFromStartToEnd();
        
        // game loop
        while (true) {
        	Position IndiActPosition = new Position(IN.nextInt(), IN.nextInt());
        	
            Direction entranceDirection = Enum.valueOf(Direction.class, IN.next()); 

            Position bestRocksPosition = null;
            
            int numberOfRocks = IN.nextInt(); // the number of rocks currently in the grid.
            
            int distance = 1000;
            for (int i = 0; i < numberOfRocks; i++) {
            	System.err.println();
                int XR = IN.nextInt();
                int YR = IN.nextInt();
                Position rockPosition = new Position(XR, YR);
                Direction entranceOfRock = Enum.valueOf(Direction.class, IN.next()); 
                
                
                tunnelMap.getRoom(rockPosition).setEntrance(entranceOfRock);
                Room nextRoomOfRock = tunnelMap.getRoom(rockPosition).getNextRoom();
                if (tunnelMap.hasFreeWayFrom(IndiActPosition) && IndiActPosition.isDangerousDistance(rockPosition) && IndiActPosition.distanceFrom(rockPosition) < distance) {
                	while(nextRoomOfRock != null && !nextRoomOfRock.isRotatable()) {
                		nextRoomOfRock = nextRoomOfRock.getNextRoom();
                	}
                    if (nextRoomOfRock != null && nextRoomOfRock.isRotatable() && IndiActPosition.isDangerousDistance(rockPosition) && IndiActPosition.distanceFrom(rockPosition) < distance) {
                    	distance = IndiActPosition.distanceFrom(rockPosition);
                    	bestRocksPosition = nextRoomOfRock.getPosition();
    				}					
				}

                System.err.println("ROCK " + rockPosition + " " + entranceOfRock);
            }
            
            String command;
            if (bestRocksPosition != null && tunnelMap.hasFreeWayFrom(IndiActPosition)) {
            	command = bestRocksPosition + " RIGHT";
			}
            else {
            	command = tunnelMap.nextCommand();	
            }
     
            
             
            // One line containing the X Y coordinates of the room in which you believe Indy will be on the next turn.
            System.out.println(command);
        }
    }

	private static void fillTunelMap(Grid tunnelMap) {
		for (int actHeight = 0; actHeight < tunnelMap.getHeight(); actHeight++) {
			String[] floor = getLevelRooms(); // represents a line in the grid and contains "width" integers. Each integer represents one room of a given type.
            
			for (int actWidth = 0; actWidth < tunnelMap.getWidth(); actWidth++) {
				Position position = new Position(actWidth, actHeight);
            	Room room = mapStringToRoom(floor[actWidth], position, tunnelMap);
            	if (actHeight == 0 && room.isGateWay() &&  !room.isRotatable()) {
					tunnelMap.setStartPosition(position);
				}
            	
			}
        }
	}

	private static Room mapStringToRoom(String roomValue, Position position, Grid tunnelMap) {
		RoomType type = Enum.valueOf(RoomType.class, "TYPE_" + roomValue.replace("-",""));
		boolean rotatable = isNegativeType(roomValue) ? false : true;
		
		return new Room(type, position, rotatable, tunnelMap);
	}

	private static boolean isNegativeType(String roomName) {
		return roomName.charAt(0) == '-';
	}

	private static String[] getLevelRooms() {
		String levelRooms = IN.nextLine();
		System.err.println(levelRooms);
		return levelRooms.split("\\s+");
	}
}