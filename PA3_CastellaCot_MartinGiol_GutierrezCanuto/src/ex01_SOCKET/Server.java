package ex01_SOCKET;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class Server extends Thread {

    private Socket socket;
    private int targetNumber, totalAttempts, totalGuessed;
    private ArrayList<Integer> attempts;
    
    public Server(Socket socket) {
        this.socket = socket;
        this.attempts = new ArrayList<>();
        this.totalAttempts = 0;
        this.totalGuessed = 0;
    }

    /* MAIN IS THE LAUNCHER */
    public static void main(String[] args) throws IOException {
    	
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("Server started. Now listening to port 6666");

        while (true) {
            Socket socket = serverSocket.accept();
            new Server(socket).start();
        }
    }

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

            String requestMessage;

            while ((requestMessage = inputChannel.readLine()) != null) {
                Request request = new Request(requestMessage);

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
                        return;
                }
            }

        } catch (IOException e) {}

        try { socket.close(); } catch (IOException e) {}
    }

    private void reset(Request request, PrintWriter output) {
    	
        updateNumberAttempts();
    	
        targetNumber = new Random().nextInt(999) + 1;
        attempts.clear();

        if (request.cheat) output.println("OK RESET " + targetNumber);
        else output.println("OK RESET");
        
    }

    private void check(Request request, PrintWriter output) {
        
    	if (attempts.contains(request.value)) output.println("REPETITION");
        
        else {
            attempts.add(request.value);
            if (request.value == targetNumber) {
            	totalGuessed++;
            	output.println("EQUAL");
            }
            else if (request.value < targetNumber) output.println("HIGHER");
            else output.println("LOWER");
        }
    }

    private void list(PrintWriter output) {
        String attemptsString = attempts.toString();
        output.println(attemptsString);
    }

    private void terminate(PrintWriter output) {
    	updateNumberAttempts();
        output.println("GOODBYE! Numbers guessed: " + totalGuessed + ", Total attempts: " + totalAttempts);        
    }
    
    private void updateNumberAttempts() {
    	totalAttempts += attempts.size();
    }
}

// Utility class. Makes requests out of strings
class Request {

    public enum Type {CHECK, RESET, TERMINATE, LIST, UNKNOWN}

    public int value;
    public Type type;
    public String message;
    public boolean cheat = false;

    // Make a request object out of a message...
    public Request(String message) {
        this.message = message;
        String[] elements = message.split(" ");
        if (elements[0].equalsIgnoreCase("check")) {
            try {
                this.value = Integer.parseInt(elements[1]);
                this.type = Type.CHECK;
                return;
            } catch (Exception ex) {
                this.type = Type.UNKNOWN;
                return;
            }
        }
        if (elements[0].equalsIgnoreCase("reset")) {
            this.type = Type.RESET;
            if (elements.length == 2 && elements[1].equalsIgnoreCase("cheat")) {
                cheat = true;
            }
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