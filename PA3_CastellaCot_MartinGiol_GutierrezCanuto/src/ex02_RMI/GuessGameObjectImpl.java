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
	
	private Map<Integer, ClientRep> clients = new HashMap<>();
	private int nextId = 0;
	
	protected GuessGameObjectImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int startGame() throws RemoteException {
		// TODO Auto-generated method stub
		int id = nextId;
		nextId++;	
        ClientRep client = new ClientRep();
        client.theNumber = generateRandomNumber();
        synchronized (clients) {
        	clients.put(id, client);
        }
        return id;
	}

	@Override
	public String check(int id, int number) throws RemoteException {
		// TODO Auto-generated method stub
		
		ClientRep client;
		synchronized (clients) {
			client = clients.get(id);
		}
		
        if (client == null) throw new RemoteException("Unknown client id");

        synchronized (client) {
        
	        if (client.justGuessed) throw new RemoteException("You have already guessed the number. Do a RESET to continue.");
	        
	        if (client.numbersChecked.contains(number)) return "REPETITION";
	        
	        client.numbersChecked.add(number);
	        
	        client.attempts++;
	        
	        if (number == client.theNumber) {
	            client.justGuessed = true;
	            client.guessed++;
	            return "EQUAL";
	        }
	        
	        else if (number < client.theNumber) return "HIGHER";
	        
	        else return "LOWER";  
        }
	}

	@Override
	public List<Integer> list(int id) throws RemoteException {
		// TODO Auto-generated method stub
		
		ClientRep client;
		synchronized (clients) {
			client = clients.get(id);
		}
		
        if (client == null) throw new RemoteException("Unknown client id");
        
        synchronized (client) {
        	return new ArrayList<>(client.numbersChecked);
        }
	}

	@Override
	public String reset(int id, boolean cheat) throws RemoteException {
		// TODO Auto-generated method stub
		
		ClientRep client;
	    synchronized (clients) {
	        client = clients.get(id);
	    }
		
        if (client == null) throw new RemoteException("Unknown client id");
	        
	        synchronized (client) {
	        client.theNumber = generateRandomNumber();
	        client.justGuessed = false;
	        client.numbersChecked.clear();
	
	        if (cheat) return "OK RESET " + client.theNumber;
	        else return "OK RESET";
        }
	}

	@Override
	public String terminate(int id) throws RemoteException {
		// TODO Auto-generated method stub
		
		ClientRep client;
	    synchronized (clients) {
	        client = clients.remove(id);
	    }
		
        if (client == null)  throw new RemoteException("Unknown client id");

        return "GOODBYE! Numbers guessed: " + client.guessed + ", Total attempts: " + client.attempts;
	}
	
	/* COMPLETE */
	private int generateRandomNumber() {
		Random ran = new Random();
		int num = ran.nextInt(999) + 1;
        return num;
    }
	
}

// utility class to represent clients (stores all relevant info regarding a client)
class ClientRep {
	
	/* COMPLETE if needed */
	
	boolean justGuessed = false;  // true if client guessed the number in the last check
	int theNumber; // number client has to guess
	int attempts = 0; // total number of attempts made
	int guessed = 0; // numbers guessed
	List<Integer> numbersChecked = new ArrayList<>();
}
