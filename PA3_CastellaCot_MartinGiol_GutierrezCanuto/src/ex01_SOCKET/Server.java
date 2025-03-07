package ex01_SOCKET;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Server extends Thread  {
	
	/* COMPLETE */
	
	private Socket socket;
    private static Map<Socket, Integer> clientNumbers = new HashMap<>();
    private static Map<Socket, ArrayList<Integer>> clientAttempts = new HashMap<>();
	
	public Server(Socket socket) {
        this.socket = socket;
    }
	
	/* MAIN IS THE LAUNCHER */
	public static void main (String [] args) throws IOException {
		/*  COMPLETE */
		
		ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("Server started. Now listenning to port 6666");
        
        while (true) {
            Socket socket = serverSocket.accept();
            new Server(socket).start();
        }
        
	
	}
	
	/* COMPLETE */
	
	@Override
    public void run() {
		
		try {
			// request channel
			BufferedReader inputChannel = new BufferedReader(
					new InputStreamReader(
							socket.getInputStream()));
			
			// reply channel
			PrintWriter outputChannel = new PrintWriter(
					socket.getOutputStream(), true);
			
            String requestString;
            
            while ((requestString = inputChannel.readLine()) != null) {
                Request request = new Request(requestString);
                
                switch (request.type) {
                    case RESET:
                    	reset(request, outputChannel);
                        break;
                    case CHECK:
                        check(request, outputChannel);
                        break;
                    case LIST:
                        list(outputChannel);
                        break;
                    case TERMINATE:
                        terminate(outputChannel);
                        break;
                    default:
                    	return;
                }
            }
            
		} catch (IOException e) {}
        
        try { socket.close(); } catch (IOException e) {}
          
    }

    private void reset(Request request, PrintWriter output) {
    	
    	int number = new Random().nextInt(999) + 1;
        clientNumbers.put(socket, number);
        clientAttempts.put(socket, new ArrayList<>());
        
        if (request.cheat) output.println("OK RESET " + number);
        else output.println("OK RESET");
        
    }

    private void check(Request request, PrintWriter output) {
        
    	ArrayList<Integer> attempts = clientAttempts.get(socket);
        
        if (attempts.contains(request.value)) output.println("REPETITION");
         
        else {
            attempts.add(request.value);
            
            int numberToGuess = clientNumbers.get(socket);
            
            if (request.value == numberToGuess) output.println("EQUAL");
            else if (request.value < numberToGuess) output.println("HIGHER");
            else output.println("LOWER");
            
        }
    }

    private void list(PrintWriter output) {
    	ArrayList<Integer> attemptList = clientAttempts.get(socket);
        String attemptsString = attemptList.toString();
        output.println(attemptsString);
    }

	private void terminate(PrintWriter output) {
		
		ArrayList<Integer> finalAttempts = clientAttempts.get(socket);
		int guessedNumbers = 0;
		int targetNumber = clientNumbers.get(socket);

		for (int attempt : finalAttempts) {
			if (attempt == targetNumber) guessedNumbers++;
		}

		output.println("GOODBYE! Numbers guessed: " + guessedNumbers + ", Total attempts: " + finalAttempts.size());
	}
    
}


//utility class. Makes requests out of strings
class Request {
	
	public enum Type {CHECK, RESET, TERMINATE, LIST,  UNKNOWN};
	
	public int value;
	public Type type;
	public String message;
	public boolean cheat = false;
	
	// make a request object out of a message...
	public Request (String message) {
		this.message = message;
		String [] elements =  message.split(" ");
		if (elements[0].equalsIgnoreCase("check")) {
			try {
				this.value = Integer.parseInt(elements[1]);
				this.type = Type.CHECK;
				return;
			}
			catch (Exception ex) {
				this.type = Type.UNKNOWN;
				return;
			}
		}
		if (elements[0].equalsIgnoreCase("reset")) {
			this.type=Type.RESET;
			if (elements.length==2 && elements[1].equalsIgnoreCase("cheat")) cheat = true;
			return;
		}
		if (elements[0].equalsIgnoreCase("terminate")) {
			this.type = Type.TERMINATE;
			return;
		}
		if (elements[0].equalsIgnoreCase("list")) {
			this.type = Type.LIST;
			return;
		}
		this.type = Type.UNKNOWN;
	}
}
