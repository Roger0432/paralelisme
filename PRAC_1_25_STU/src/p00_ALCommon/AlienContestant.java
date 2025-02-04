package p00_ALCommon;

import java.io.*;

public class AlienContestant extends Thread {
	
	private String raceName;
	private int id;
	private LotteryMat mat;
	
	public AlienContestant (String raceName, int id, 
			                LotteryMat environment) {
		this.raceName = raceName;
		this.id = id;
		this.mat = environment;
	}
	
	public void run () {
		while (true) {
			// try betting is non-blocking.
			if (mat.tryBetting(raceName, id)) {
				mat.putChip(raceName, id);
				mat.endBetting();
			}
			else {
				// if not allowed to place a bet, contestant can do other things
				AlienContestant.cannotBetNow(raceName+"("+id+")");
				//Thread.yield();
				// sleep seems to alleviate congestion when using implicit locks
				try {Thread.sleep(0,1);} catch (InterruptedException iex) {}
			}
		}
	}
	
	private static BufferedWriter bfw;
	private static volatile int lc = 0;
	
	static {
		try {
			bfw = new BufferedWriter(new FileWriter ("control.txt", false));
		}
		catch (IOException ioex) {
			System.err.println("System cannot start");
			System.exit(1);
		}
	}
	
	private static void cannotBetNow (String s) {
		if (lc>=1000) return;
		lc++;
		try {
			bfw.write(s);
			bfw.newLine();
		}
		catch (IOException ioex) {
			System.err.println("exception writing file: "+ioex);
		}
	}
	
	public static void close() {
		try {
			bfw.close();
		}
		catch (IOException ioex) {
		}
	}
	
} //end of class AlienContestant