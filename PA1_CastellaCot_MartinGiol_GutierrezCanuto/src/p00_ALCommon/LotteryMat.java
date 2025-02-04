package p00_ALCommon;

import java.util.Random;

public abstract class LotteryMat {
	
	private final int NUM_DRAWERS; // set at construction time
	public final int NUM_SQUARES = 4;
	private Random alea;
	private StringBuilder [] squares;
	protected volatile int emptySquares;
	
	protected String lastWinnerRace = "none"; // keeps track of the name of the race that won last game 
	protected volatile int currentDrawerId; // id of the drawer that should work next 
	
	public LotteryMat (int nd) {
		alea = new Random();
		squares = new StringBuilder[NUM_SQUARES];
		for (int i=0; i<NUM_SQUARES; i++) {
			squares[i] = new StringBuilder();
		}
		NUM_DRAWERS = nd;
		currentDrawerId = nd-1;
		restart();
	}
	
	/* 
	 Contestants invoke this method to get access to the mat.
	 When this method returns true...
	 - constestant has exclusive access over the mat
	 - rules apply...
	 
	 If access is not possible (rules do not apply) then method returns false
	 (BEWARE: it does not keep contestant blocked)
	*/
	public abstract boolean tryBetting (String raceName, int memberId);
	
	/* contestants invoke this method to release access... after they've
	placed their chip */
	public abstract void endBetting();
	
	/* Imperial drawers use this method to get exclusive access
	 to the mat. Contrary to tryBetting, this method 
	 has blocking nature. It blocks the invoker until 
	 the drawing can be performed */
	public abstract void startDrawing (int drawerId);
	
	/* Invoked by drawers when drawing has finished and winner
	has been announced */ 
	public abstract void endDrawing();
	
	/* Place a chip on the mat */
	public void putChip (String raceName, int raceId) {
		int sp;
		
		if (emptySquares == 0) {
			System.err.println("RULE VIOLATION: attempting to put a chip on a full mat");
			System.exit(1);
		}
		
		emptySquares--;
		
		// first get a random free square (its index)
		sp = alea.nextInt(NUM_SQUARES);
		while (!squares[sp].toString().equals("FREE")) {
			Thread.yield();
			sp = alea.nextInt(NUM_SQUARES);
		}
		
		// then put the chip
		Thread.yield();
		squares[sp].setLength(0); // empties the stringbuffer
		for (int i=0; i<raceName.length(); i++) {
			Thread.yield();
			squares[sp].append(raceName.charAt(i));
			Thread.yield();
		}
		squares[sp].append('(');
		Thread.yield();
		squares[sp].append(Integer.toString(raceId));
		Thread.yield();
		squares[sp].append(')');
	}
	
	/* true if the given race already participates in the ongoing hand */
	protected boolean participatesInCurrentHand (String raceName) {
		for (int i=0; i<squares.length; i++) {
			if (squares[i].toString().startsWith(raceName)) 
				return true;
		}
		return false;
	}
	
	/* Used by drawers to get the four chips on the surface */
	protected String [] getChips () {
		
		String [] result = new String[NUM_SQUARES];
		for (int i=0; i<NUM_SQUARES; i++) {
			result[i] = squares[i].toString();
		}
		return result;
	}
	
	/* Invoked by drawers. Restarts the surface */  
	protected void restart () {
		
		for (int i=0; i<NUM_SQUARES; i++) {
			squares[i].setLength(0);
			squares[i].append("FREE");
		}
		emptySquares = 4;
		currentDrawerId = (currentDrawerId+1)%NUM_DRAWERS;
	}
	
} // end of class
