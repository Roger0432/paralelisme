package p03_JJECommon;

public interface Synchronizer {
	public void letMeJump (int id);
	public void jumpDone (int id);
	
	public void letMeJive (int id);
	public void jiveDone (int id);
	
	public boolean letMeEnjoy (int id);  // returns true for ENJOY, false for JOY 
	public void enjoyDone (int id);
	
	public default void writeString (String s) {
		CuteKerberos.writeString (s);
	}
}
