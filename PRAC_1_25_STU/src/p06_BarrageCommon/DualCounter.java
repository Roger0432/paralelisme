package p06_BarrageCommon;

public class DualCounter {
	public volatile int syncCounter = 0;
	public volatile int unsyncCounter = 0;
	
	/* COMPLETE */
	
	public int getSyncCount () {return syncCounter;}
	public int getUnsyncCount () {return unsyncCounter;}
	
	public void increment () {
		int temp;
		
		temp = unsyncCounter;
		temp ++ ;
		Thread.yield();
		unsyncCounter = temp;
		
		preProtocol();
		temp = syncCounter;
		temp ++ ;
		Thread.yield();
		syncCounter = temp;
		postProtocol();
	}
	
	private void preProtocol () {
		/* COMPLETE */
	}
	
	private void postProtocol () {
		/* COMPLETE */
	}
	
	public void reset () {
		syncCounter = 0;
		unsyncCounter = 0;
	}
		
}