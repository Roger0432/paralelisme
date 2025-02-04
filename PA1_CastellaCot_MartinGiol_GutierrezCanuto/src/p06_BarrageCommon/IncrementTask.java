package p06_BarrageCommon;

public class IncrementTask implements Runnable {
	
	private DualCounter counter;
	
	public IncrementTask (DualCounter counter) {
		this.counter = counter;
	}
	
	public void run () {
		counter.increment();
	}
}