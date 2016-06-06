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
		RoomType ratated = this;
		
		switch (this) {
		case TYPE_2:
			ratated = TYPE_3;
			break;
		case TYPE_3:
			ratated = TYPE_2;
			break;
		case TYPE_4:
			ratated = TYPE_5;
			break;	
		case TYPE_5:
			ratated = TYPE_4;
			break;	
		case TYPE_6:
			ratated = TYPE_7;
			break;	
		case TYPE_7:
			ratated = TYPE_8;
			break;		
		case TYPE_8:
			ratated = TYPE_9;
			break;		
		case TYPE_9:
			ratated = TYPE_6;
			break;		
		case TYPE_10:
			ratated = TYPE_11;
			break;		
		case TYPE_11:
			ratated = TYPE_12;
			break;		
		case TYPE_12:
			ratated = TYPE_13;
			break;		
		case TYPE_13:
			ratated = TYPE_10;
			break;		
		default:
			break;
		}
		
		return ratated;
	}
	
	public RoomType rotateLeft() {
		RoomType ratated = this;
		
		switch (this) {
		case TYPE_2:
			ratated = TYPE_3;
			break;
		case TYPE_3:
			ratated = TYPE_2;
			break;
		case TYPE_4:
			ratated = TYPE_5;
			break;	
		case TYPE_5:
			ratated = TYPE_4;
			break;	
		case TYPE_6:
			ratated = TYPE_9;
			break;	
		case TYPE_7:
			ratated = TYPE_6;
			break;		
		case TYPE_8:
			ratated = TYPE_7;
			break;		
		case TYPE_9:
			ratated = TYPE_8;
			break;		
		case TYPE_10:
			ratated = TYPE_13;
			break;		
		case TYPE_11:
			ratated = TYPE_10;
			break;		
		case TYPE_12:
			ratated = TYPE_11;
			break;	
		case TYPE_13:
			ratated = TYPE_12;
			break;	
		default:
			break;
		}
		
		return ratated;
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
	public boolean equals(Object otherObject) {
		// a quick test to see if the objects are identical
		if (this == otherObject) return true;
		
		// must return false if the explicit parameter is null
		if (otherObject == null) return false;
		
		// if the classes don't match, they can't be equal
		if (getClass() != otherObject.getClass()) return false;
		
		// now we know otherObject is a non-null Employee
		Position other = (Position) otherObject;
		
		// test whether the fields have identical values
		return Objects.equals(width, other.width) && height == other.height;
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(width, width);
	}
}

class Room {
	
	private RoomType type;
	
	private boolean rotatable;
	
	private Position position;
	
	private Direction entrance = null;
	
	private Direction exit = null;
	
	public Room(RoomType type, Position position, boolean rotatable) {
		this.type = type;
		this.position = position;
		this.rotatable = rotatable;
	}
	
	private Room(RoomType type, Position position, boolean rotatable, Direction entrance, Direction exit) {
		this(type, position, rotatable);
		this.entrance = entrance;
		this.exit = exit;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public RoomType getType() {
		return type;
	}

	public void setType(RoomType type) {
		this.type = type;
	}

	public boolean isRotatable() {
		return rotatable;
	}

	public void setRotatable(boolean rotatable) {
		this.rotatable = rotatable;
	}

	public Direction getEntrance() {
		return this.type.hasEntrance(entrance) ? entrance : null;
	}

	public void setEntrance(Direction entrance) {
		this.entrance = entrance;
	}

	public Direction getExit() {
		exit = type.getExitByEntrance(getEntrance());
		return exit;
	}

	public void setExit(Direction exit) {
		this.exit = exit;
	}
	
	public Room rotateLeft() {
		return new Room(this.type.rotateLeft(), this.position, this.rotatable, this.entrance, this.exit);
	}
	
	public Room rotateRight() {
		return new Room(this.type.rotateRight(), this.position, this.rotatable, this.entrance, this.exit);
	}
	
	public Room getNextRoom(Grid grid) {
		Room nextRoom = null;
		System.err.println("entrance " + this.getEntrance());
		System.err.println("exit " + this.getExit());
		System.err.println("type " + this.type);
		
		if (this.getExit() == Direction.LEFT) {
			nextRoom = grid.getRoom(this.position.goLeft());
			System.err.println("LEFT");
		}
		else if (this.getExit() == Direction.RIGHT) {
			nextRoom = grid.getRoom(this.position.goRight());
			System.err.println("RIGHT");
		}
		else if (this.getExit() == Direction.DOWN) {
			nextRoom = grid.getRoom(this.position.goDown());
			System.err.println("DOWN");
		}
		if (nextRoom != null) {
			nextRoom.entrance = this.exit.opposite();
		}		
		return nextRoom;
	}

	public boolean isValidDestination() {
		return this.type != RoomType.TYPE_0;
	}
	
}

class Grid {

	Map<Position, Room> rooms = new HashMap<>();
	
	private int height;
	private int width;
	
	public Grid(int width, int height) {
		this.width = width;
		this.height = height; 
	}
	
	Room getRoom(Position position) {
		return rooms.get(position);
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

	public String nextCommand(Position startPos, Direction startPosEntrance) {
		String nextCommand = "WAIT";
		
		Room startRoom = getRoom(startPos);
		
		startRoom.setEntrance(startPosEntrance);
	
		
		Room middleRoom = startRoom.getNextRoom(this);
		
		Room endRoom = middleRoom.getNextRoom(this);
		
		if ( endRoom == null || !endRoom.isValidDestination() ) {
			Position positionToRotate = middleRoom.getPosition();
			

			System.err.println("moddleRoom " + middleRoom.getType() );
			
			Room endRoomIfRotateRight = middleRoom.rotateRight().getNextRoom(this);
			Room endRoomIfRotateLeft = middleRoom.rotateLeft().getNextRoom(this);
			
			if (endRoomIfRotateRight != null) {
				System.err.println("right end " + endRoomIfRotateRight.getType());
			}
			
			if (endRoomIfRotateLeft != null) {
				System.err.println("left end" + endRoomIfRotateLeft.getType());
			}
			
			if ( endRoomIfRotateRight != null && endRoomIfRotateRight.isValidDestination()) {				
				setRoom(positionToRotate,middleRoom.rotateRight());
				nextCommand = positionToRotate.getWidth() + " " + positionToRotate.getHeight() + " " + "RIGHT";			
			}
			else if (endRoomIfRotateLeft != null && endRoomIfRotateLeft.isValidDestination()) {
				setRoom(positionToRotate,middleRoom.rotateLeft());			
				nextCommand = positionToRotate.getWidth() + " " + positionToRotate.getHeight() + " " + "LEFT";		
			}
		}
		else {
			System.err.println(middleRoom);
			System.err.println("SHIT HAPPENS");
		}
		
		return nextCommand;
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
        
        // game loop
        while (true) {
        	Position actPosition = new Position(IN.nextInt(), IN.nextInt());
        	
            Direction entranceDirection = Enum.valueOf(Direction.class, IN.next()); 

            int numberOfRocks = IN.nextInt(); // the number of rocks currently in the grid.
            for (int i = 0; i < numberOfRocks; i++) {
            	System.err.println();
                int XR = IN.nextInt();
                int YR = IN.nextInt();
                String POSR = IN.next();
            }
     
            String command = tunnelMap.nextCommand(actPosition, entranceDirection);
             
            // One line containing the X Y coordinates of the room in which you believe Indy will be on the next turn.
            System.out.println(command);
        }
    }

	private static void fillTunelMap(Grid tunnelMap) {
		for (int actHeight = 0; actHeight < tunnelMap.getHeight(); actHeight++) {
			String[] floor = getLevelRooms(); // represents a line in the grid and contains "width" integers. Each integer represents one room of a given type.
            
			for (int actWidth = 0; actWidth < tunnelMap.getWidth(); actWidth++) {
				Position position = new Position(actWidth, actHeight);
            	Room room = mapStringToRoom(floor[actWidth], position);
            	
            	tunnelMap.setRoom(position, room);
			}
        }
	}

	private static Room mapStringToRoom(String roomValue, Position position) {
		RoomType type = Enum.valueOf(RoomType.class, "TYPE_" + roomValue.replace("-",""));
		boolean rotatable = isNegativeType(roomValue) ? false : true;
		
		return new Room(type, position, rotatable);
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