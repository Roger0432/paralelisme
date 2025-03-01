package p5_QuintupletsAlternative;


import java.util.concurrent.Semaphore;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import p3_QuintupletsCommon.*;

public class QASynchronizer implements QuintupletSynchronizer{
	
	/* COMPLETE */
	private Semaphore multiplex = new Semaphore(3);
    private CyclicBarrier barrier = new CyclicBarrier(5);
	
	public void goPlayground (Quintuplet q) {
		QAnalyser.injectTrace(q+" is in the playground ready to play when all together");
		/* COMPLETE from this point. Previous line must be the first one */
		
		try { barrier.await(); } catch (InterruptedException | BrokenBarrierException e) {}
		
			
	}
	
	public void startPlaying (Quintuplet q) {
		/* COMPLETE */
		
		multiplex.acquireUninterruptibly();
		
		QAnalyser.injectTrace(q+" is having fun with the tickadiddle"); // this line must be the very last
	}
	
	public void endPlaying (Quintuplet q) {
		QAnalyser.injectTrace(q+" is about to leave the tickadiddle");
		/* COMPLETE from this point. Previous line must be the first one */
		
		multiplex.release();
		
	}
	
	public void goHomeForASnack (Quintuplet q) {
		/* COMPLETE */
		
		try { barrier.await(); } catch (InterruptedException | BrokenBarrierException e) {}
		
		QAnalyser.injectTrace(q+" is having a snack at home"); // this line must be the very last
	}
}
