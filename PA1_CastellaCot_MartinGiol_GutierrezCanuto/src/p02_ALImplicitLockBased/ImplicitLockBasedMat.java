package p02_ALImplicitLockBased;

import p00_ALCommon.LotteryMat;

public class ImplicitLockBasedMat extends LotteryMat {

    private volatile boolean isBetting;
    private volatile boolean isDrawing;

    public ImplicitLockBasedMat(int numDrawers) {
        super(numDrawers);
        isBetting = false;
        isDrawing = false;
    }

    @Override
    public boolean tryBetting(String raceName, int memberId) {
    	synchronized (this) {
            if (!isBetting && emptySquares != 0 && !participatesInCurrentHand(raceName) && !lastWinnerRace.equals(raceName)) {
            	isBetting = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public void endBetting() {
    	isBetting = false;
    }

    @Override
    public void startDrawing(int drawerId) {
        boolean okToEnter = false;
        while (!okToEnter) {
            synchronized (this) {
                if (!isBetting && !isDrawing && emptySquares == 0 && drawerId == currentDrawerId) {
                    isDrawing = true;
                    okToEnter = true;
                }
            }
            if (!okToEnter) Thread.yield();
        }
    }

    @Override
    public void endDrawing() {
    	isDrawing = false; 
    }
}