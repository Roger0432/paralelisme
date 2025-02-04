package p04_JJECompareAndSet;

import p03_JJECommon.*;

public class CSLauncher {
	public static void main (String [] args) throws Exception {
		int INSTANCES = 5;
		
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		System.out.println("\nLaunching CS solution with n="+INSTANCES+"\n");
		System.out.print("Press RETURN to start. Press return again to stop the frenzy");
		scanner.nextLine();
		
		Synchronizer synchro = new CSBasedSynchronizer();
		
		Jump [] jumps = new Jump[INSTANCES];
		Jive [] jives = new Jive[INSTANCES];
		Enjoy [] enjoys = new Enjoy[INSTANCES];
		
		for (int i=0; i<jumps.length; i++) {
			jumps[i] = new Jump(i, synchro);
		}
		for (int i=0; i<jives.length; i++) {
			jives[i] = new Jive(i, synchro);
		}
		for (int i=0; i<enjoys.length; i++) {
			enjoys[i] = new Enjoy(i, synchro);
		}
		
		for (int i=0; i<jumps.length; i++) {
			new Thread(jumps[i]).start();
		}
		for (int i=0; i<enjoys.length; i++) {
			new Thread(enjoys[i]).start();
		}
		for (int i=0; i<jives.length; i++) {
			new Thread(jives[i]).start();
		}
		
		scanner.nextLine();
		
		System.out.println("\n\nCS-BASED TERMINATING...\n");
		
		System.exit(0);
	}
}
