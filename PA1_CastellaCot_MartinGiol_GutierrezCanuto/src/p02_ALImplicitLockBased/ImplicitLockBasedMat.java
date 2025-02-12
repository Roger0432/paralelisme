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
        //System.out.println("tryBetting (" + raceName + "," + memberId + ")");
        synchronized (this) {
            if (isBetting || 
            		emptySquares == 0 || 
            		participatesInCurrentHand(raceName) || 
            		lastWinnerRace.equals(raceName)) {
                return false;
            }
            isBetting = true;
            return true;
        }
    }

    @Override
    public void endBetting() {
        //System.out.println("endBetting");
        synchronized (this) {
            isBetting = false;
        }
    }

    @Override
    public void startDrawing(int drawerId) {
        //System.out.println("startDrawing(" + drawerId + ")");
        synchronized (this) {
            while (currentDrawerId != drawerId || !isDrawing || emptySquares != 0) {
                Thread.yield();
            }
            isDrawing = true;
        }
    }

    @Override
    public void endDrawing() {
        //System.out.println("endDrawing");
        synchronized (this) {
            isDrawing = false; 
        }
    }
}