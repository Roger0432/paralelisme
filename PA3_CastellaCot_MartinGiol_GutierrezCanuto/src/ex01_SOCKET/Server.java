package ex01_SOCKET;

import java.io.IOException;

public class Server extends Thread  {
	
	/* COMPLETE */
	
	/* MAIN IS THE LAUNCHER */
	public static void main (String [] args) throws IOException {
		/*  COMPLETE */
	}
	
	/* COMPLETE */
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
