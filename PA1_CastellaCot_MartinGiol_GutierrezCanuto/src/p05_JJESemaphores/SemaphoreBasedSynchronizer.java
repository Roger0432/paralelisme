package p05_JJESemaphores;

import p03_JJECommon.*;
import java.util.concurrent.*;

public class SemaphoreBasedSynchronizer implements Synchronizer {

	/* COMPLETE */
	// you may use as many simple-typed variables as you deem necessary and three
	// semaphores
	private Semaphore canJump = new Semaphore(1);
	private Semaphore canJive = new Semaphore(0);
	private Semaphore canJoy = new Semaphore(0);	
	private volatile int lastJumpId = -1;
	private volatile int jumpCount = 0;
	private volatile int jiveCount = 0;

	@Override
	public void letMeJump(int id) {
		try { canJump.acquire();} catch (InterruptedException e) {}
		while (lastJumpId == id) {
			canJump.release();
			Thread.yield();
			try { canJump.acquire();} catch (InterruptedException e) {}
		}
		jumpCount++;
		lastJumpId = id;
	}

	@Override
	public void jumpDone(int id) {
		if (jumpCount == 1) canJump.release();
		else if (jumpCount == 2) {
			jumpCount = 0;
			if (lastJumpId != 0) canJive.release();
			else canJoy.release();
		}
	}

	@Override
	public void letMeJive(int id) {
		try { canJive.acquire();} catch (InterruptedException e) {}
		jiveCount++;
	}

	@Override
	public void jiveDone(int id) {
		if (jiveCount < lastJumpId) canJive.release();
		else {
			jiveCount = 0;
			canJoy.release();
		}
	}

	@Override
	public boolean letMeEnjoy(int id) {
		try { canJoy.acquire();} catch (InterruptedException e) {}
		if (lastJumpId % 2 == 0) return false; //JOY
		else return true; //ENJOY
	}

	@Override
	public void enjoyDone(int id) {
		canJump.release();
	}

}