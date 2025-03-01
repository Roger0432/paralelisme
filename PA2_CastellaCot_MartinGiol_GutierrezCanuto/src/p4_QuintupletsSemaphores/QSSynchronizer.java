package p4_QuintupletsSemaphores;

import java.util.concurrent.Semaphore;

import p3_QuintupletsCommon.*;

public class QSSynchronizer implements QuintupletSynchronizer {

	/* COMPLETE */
	private int counter = 0;
	private Semaphore mutex = new Semaphore(1);
	private Semaphore multiplex = new Semaphore(3); // for tickadiddle
	private Semaphore turnstile1 = new Semaphore(0); // for arriving at the playground
	private Semaphore turnstile2 = new Semaphore(0); // for leaving the playground

	public void goPlayground(Quintuplet q) {
		QAnalyser.injectTrace(q + " is in the playground ready to play when all together");
		/* COMPLETE from this point. Previous line must be the first one */

		mutex.acquireUninterruptibly();
		counter++;
		if (counter == 5) turnstile1.release(5); // preload 1st barrier
		mutex.release();

		turnstile1.acquireUninterruptibly();

		// critical point
	}

	public void startPlaying(Quintuplet q) {
		/* COMPLETE */

		multiplex.acquireUninterruptibly();

		QAnalyser.injectTrace(q + " is having fun with the tickadiddle"); // this line must be the very last
	}

	public void endPlaying(Quintuplet q) {
		QAnalyser.injectTrace(q + " is about to leave the tickadiddle");
		/* COMPLETE from this point. Previous line must be the first one */

		multiplex.release();

	}

	public void goHomeForASnack(Quintuplet q) {
		/* COMPLETE */

		mutex.acquireUninterruptibly();
		counter--;
		if (counter == 0) turnstile2.release(5); // preload 2nd barrier
		mutex.release();

		turnstile2.acquireUninterruptibly();

		QAnalyser.injectTrace(q + " is having a snack at home"); // this line must be the very last
	}
}
