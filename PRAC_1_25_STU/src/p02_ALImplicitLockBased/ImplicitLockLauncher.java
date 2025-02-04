package p02_ALImplicitLockBased;

import p00_ALCommon.*;

public class ImplicitLockLauncher {
	
	public static void main (String [] args) {
		int CONTESTANTS_PER_RACE = 4;
		int DRAWERS = 4;
		
		String [] races = {"WOOKIE", "KLINGON", "HUTT", "ROMULAN", "HEECHEE"};
		AlienContestant [] contestants = new AlienContestant[CONTESTANTS_PER_RACE*races.length];
		LotteryMat mat = new ImplicitLockBasedMat(DRAWERS);
		ImperialDrawer [] drawers = new ImperialDrawer[DRAWERS];
		
		int index = 0;
		
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		
		System.out.println("-- IMPLICIT LOCK --\n");
		System.out.println("\n Press enter to start");
		System.out.println(" To stop, press enter again");
		
		scanner.nextLine();
		System.out.println();
		
		for (int drawer = 0; drawer<DRAWERS; drawer++) {
			drawers[drawer] = new ImperialDrawer(drawer, mat);
			drawers[drawer].setName(drawer+"");
			drawers[drawer].start();
		}
		
		for (int race = 0; race<races.length; race++) {
			for (int id = 0; id<CONTESTANTS_PER_RACE; id++) {
				contestants[index] = new AlienContestant(races[race], id, mat);
				index++;
			}
		}
		
		for (int i=0; i<contestants.length; i++) {
			contestants [i].start();
		}
		
		scanner.nextLine();
		scanner.close();
		AlienContestant.close();
		
		System.out.println("\n\n IMPLICIT lock TERMINATING...\n");
		
		System.exit(0);
	}
}


