package p01_ALExplicitLockBased;

import java.util.concurrent.locks.ReentrantLock;

import p00_ALCommon.LotteryMat;

public class ExplicitLockBasedMat extends LotteryMat{
	
	// Declare here your explicit lock. And nothing more
	ReentrantLock lock;
	
	public ExplicitLockBasedMat (int numDrawers) {
		super(numDrawers);
		lock = new ReentrantLock();
	}

	/* COMPLETE (implement inherited abstract methods) */

	
	@Override
	public boolean tryBetting(String raceName, int memberId) {
		lock.lock();
		if (emptySquares == 0 || participatesInCurrentHand(raceName) || lastWinnerRace.equals(raceName)) {
			lock.unlock();
			return false;
		}
		return true;
	}

	@Override
	public void endBetting() {
		lock.unlock();
	}

	@Override
	public void startDrawing(int drawerId) {
		lock.lock();
		while (emptySquares > 0 || currentDrawerId != drawerId) {
			lock.unlock();
			Thread.yield();
			lock.lock();
		}
	}

	@Override
	public void endDrawing() {
		lock.unlock();
	}
	

}
















