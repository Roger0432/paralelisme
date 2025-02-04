package p03_JJECommon;

public class CuteKerberos {
	private static StringBuffer sbuffer;
	private static java.util.Random alea = new java.util.Random();
	
	public static void writeString (String string ) {
		int n;
		sbuffer = new StringBuffer();
		for (int i=0; i<string.length(); i++) {
			System.out.print(string.charAt(i));
			n = alea.nextInt(100);
			if (n>=95) try {Thread.sleep(1);} catch(Exception ex) {}
			else if (n>=75) Thread.yield();
			sbuffer.append(string.charAt(i));
		}
		parse(sbuffer.toString());
		
	}
	
	private static void parse (String s) {
		int id;
		int nature;
		try {
			id = getId(s);
			if (s.substring(0, 3).equals("JOY")) nature = JOY;
			else if (s.substring(0, 4).equals("JUMP")) nature = JUMP;
			else if (s.substring(0, 4).equals("jive")) nature = JIVE;
			else if (s.substring(0, 5).equals("ENJOY")) nature = ENJOY;
			else throw new Exception();
			
			if (nature!=expected) {
				System.err.println("ERROR "+s+" not expected now");
				System.exit(1);
			}
			
			switch (expected) {
				case JUMP: 
					jumps = (jumps + 1)%2;
					if (jumps==1) {
						// first jump
						lastJumpId = id;
					}
					else {
						// second jump
						if (id==lastJumpId) {
							System.err.println("ERROR: id repetition in JUMP");
							System.exit(1);
						}
						lastJumpId = id;
						if (lastJumpId == 0) {expected = JOY; }
						else {expected = JIVE;}
					}
					break;
					
				case JIVE: 
					jives++;
					if (jives == lastJumpId) {
						jives = 0;
						if (lastJumpId%2==0) expected=JOY;
						else expected = ENJOY;
					}
					break;
					
				case JOY: 
				case ENJOY: 
					expected = JUMP;
					break;
			}
		}
		catch (Exception e) {
			System.err.println("bad formed string: "+s);
			System.exit(1);
		}
	}
	
	private static int getId(String s) {
		int start = s.indexOf('(');
		int end = s.indexOf(')');
		return Integer.parseInt(s.substring(start+1, end));
	}
	
	private static final int JUMP = 1;
	private static final int JIVE = 2;
	private static final int JOY = 3;
	private static final int ENJOY = 4;
	
	private static volatile int expected = JUMP;
	private static volatile int jumps = 0;
	private static volatile int jives;
	private static volatile int lastJumpId = -1;
}
