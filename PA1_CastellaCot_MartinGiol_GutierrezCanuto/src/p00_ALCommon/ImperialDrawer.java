package p00_ALCommon;

import java.util.Random;

public class ImperialDrawer extends Thread {
	
	Random alea = new Random();
	
	private LotteryMat mat;
	private int id;
	
	public ImperialDrawer (int id, LotteryMat environment) {
		this.id = id;
		this.mat = environment;
	}
	
	public void run () {
		while (true) {
			mat.startDrawing(id);
			determineWinner(mat);
			mat.restart();
			mat.endDrawing();
		}
	}
	
	private void determineWinner(LotteryMat mat) {
		
		System.out.println("\t--- DRAWER("+id+") AT WORK ---");
		
		String previousWinner = mat.lastWinnerRace;
		String [] chips = mat.getChips();
		String [] races = new String[chips.length];
		int winner = alea.nextInt(chips.length);
		
		for (int i=0; i<chips.length; i++) {
			races[i] = chips[i].substring(0, chips[i].indexOf('('));
		}
		
		for (int i=0; i<chips.length; i++) {
			System.out.print("\t"+chips[i]+" --> ");
			Thread.yield();
			if (i==winner) {
				mat.lastWinnerRace = races[i];
				System.out.println("*** WINS THE HAND *** ");
			}
			else {
				System.out.println("GETS NO PRIZE");
			}
			
			if (!getName().equals(mat.currentDrawerId+"")) {
				System.err.println("RULE VIOLATION: id of drawer ("+getName()+") is not the expected: "+mat.currentDrawerId);
				System.exit(1);
			}
			
			if (chips[i].startsWith(previousWinner)) {
				System.err.println("RULE VIOLATION: "+previousWinner+"s COULD NOT PARTICIPATE. One of them won last hand");
				System.exit(1);
			}
		}
		
		for (int i=0; i<races.length; i++) {
			for (int j=i+1; j<races.length; j++) {
				if (races[i].equals(races[j])) {
					System.err.println("RULE VIOLATION: race repetition in hand");
					System.exit(1);
				}
			}
		}
		
		System.out.println();
	}
	
} // end of class ImperialDrawer