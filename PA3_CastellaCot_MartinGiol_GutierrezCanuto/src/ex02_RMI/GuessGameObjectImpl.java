package ex02_RMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;
import java.util.*;

public class GuessGameObjectImpl extends UnicastRemoteObject implements GuessGameObjectInterface{
	
	// launcher
	public static void main (String [] args)  {
		try {
			Registry registry = LocateRegistry.createRegistry(1999);
			registry.bind("GUESS", new GuessGameObjectImpl());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Guess service bound and running");
	}
	
	/* COMPLETE */
	
}

// utility class to represent clients (stores all relevant info regarding a client)
class ClientRep {
	
	/* COMPLETE if needed */
	
	boolean justGuessed = false;  // true if client guessed the number in the last check
	int theNumber; // number client has to guess
	int attempts = 0; // total number of attempts made
	int guessed = 0; // numbers guessed
}
