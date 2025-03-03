package p04_JJECompareAndSet;

import p03_JJECommon.*;
import java.util.concurrent.atomic.*;

public class CSBasedSynchronizer implements Synchronizer {

	/* COMPLETE */
	// you may use as many simple-typed variables as you deem necessary but only a
	// single instance of AtomicInteger
	AtomicInteger state;
	private final static int WRITING = 0;
	private final static int JUMP = 1;
	private final static int JIVE = 2;
	private final static int JOY = 3;
	private volatile int lastJumpId = -1;
	private volatile int jumpCount = 0;
	private volatile int jiveCount = 0;

	public CSBasedSynchronizer() {
		state = new AtomicInteger(JUMP);
	}

	@Override
	public void letMeJump(int id) {
		while (lastJumpId == id || !state.compareAndSet(JUMP, WRITING)) {
			Thread.yield();
		}
		jumpCount++;
		lastJumpId = id;
	}

	@Override
	public void jumpDone(int id) {
		if (jumpCount == 1) state.set(JUMP);
		else if (jumpCount == 2) {
			jumpCount = 0;
			if (lastJumpId != 0) state.set(JIVE);
			else state.set(JOY);
		}
	}

	@Override
	public void letMeJive(int id) {
		while (!state.compareAndSet(JIVE, WRITING)) {
			Thread.yield();
		}
		jiveCount++;
	}

	@Override
	public void jiveDone(int id) {
		if (jiveCount < lastJumpId) state.set(JIVE);
		else {
			jiveCount = 0;
			state.set(JOY);
		}
	}

	@Override
	public boolean letMeEnjoy(int id) {
		while(!state.compareAndSet(JOY, WRITING)) {
			Thread.yield();
		}
		if (lastJumpId % 2 == 0) return false; //JOY
		else return true; //ENJOY
	}

	@Override
	public void enjoyDone(int id) {
		state.set(JUMP);
	}

}