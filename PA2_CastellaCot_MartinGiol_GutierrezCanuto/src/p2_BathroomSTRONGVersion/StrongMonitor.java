package p2_BathroomSTRONGVersion;

import java.lang.reflect.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import p0_BathroomCommon.*;


public class StrongMonitor implements BathroomMonitor {

	Method writeMethod; // do not remove
	
	/* COMPLETE */
	private ReentrantLock lock;
	private Condition anteroom, stall;
	private int menInAnteroom, womenInAnteroom;
	private boolean[] freeStalls;
	
	private int nextTicketMen = 0;
    private int nextTicketWomen = 0;
    private int nextStallTicket = 0;
    
    private int currentTicketMen = 0;
    private int currentTicketWomen = 0;
    private int currentStallTicket = 0;
	
	public StrongMonitor (Analyser an) {
		try {writeMethod = an.getClass().getMethod("writeString", String.class);}
		catch(Exception ex) {System.exit(1);}
		
		/* COMPLETE */
		lock = new ReentrantLock(true);
		anteroom = lock.newCondition();
		stall = lock.newCondition();
		menInAnteroom = 0;
		womenInAnteroom = 0;
		freeStalls = new boolean[4];
		for (int i = 0; i < 4; i++) {
			freeStalls[i] = true;
		}
	}
	
	private void injectTrace(String s) {
		try {
			writeMethod.invoke(null, s);
		} catch (Exception ex) {
			System.exit(1);
		}
	}

	public void enter(Person p) { // person is in the entrace and enters to the waiting area
		/* COMPLETE */
		lock.lock();
		injectTrace("--> ENTERING bathroom " + p);
		/* COMPLETE if needed */
		lock.unlock();
	}

	public void accessAnteroom(Person p) { // person is in the waiting area and wants to enter the anteroom
		/* COMPLETE if needed */
		lock.lock();
		
		Gender genderP = p.getGender();
		
		if (genderP == Gender.MAN) {
			p.giveTicket(nextTicketMen);
			nextTicketMen++;
		}
		else {
			p.giveTicket(nextTicketWomen);
			nextTicketWomen++;
		}

		while ((genderP == Gender.MAN && womenInAnteroom > 0)
                || (genderP == Gender.WOMAN && menInAnteroom > 0)
                || (genderP == Gender.MAN && p.getTicket() != currentTicketMen)
                || (genderP == Gender.WOMAN && p.getTicket() != currentTicketWomen)) {
            anteroom.awaitUninterruptibly();
        }

		if (p.getGender() == Gender.MAN) {
			menInAnteroom++;
			currentTicketMen++;
		}
		else {
			womenInAnteroom++;
			currentTicketWomen++;
		}

		injectTrace("\t--> ACCESSING the anteroom " + p);

		/* COMPLETE if needed */
		lock.unlock();
	}

	public void getFreeStall(Person p) { // person is in the anteroom and wants a free stall
		// BEWARE: invoke p.assignStall when a free stall for p has been found

		/* COMPLETE if needed */
		lock.lock();
		
		p.giveTicket(nextStallTicket);
		nextStallTicket++;

		int stallNum = -1;

		while (stallNum == -1) {
            while (p.getTicket() != currentStallTicket) {
                stall.awaitUninterruptibly();
            }

            int i = 0;
            while (i < freeStalls.length && !freeStalls[i]) {
                i++;
            }

            if (i < freeStalls.length) {
                stallNum = i;
                freeStalls[i] = false;
                p.assignStall(stallNum);
                currentStallTicket++;
            } 
            else {
                stall.awaitUninterruptibly();
            }
        }
		injectTrace("\t\t " + p + " HAS TAKEN Stall [" + stallNum + "]");

		/* COMPLETE if needed */
		lock.unlock();
	}

	public void exit(Person p) {
		/* COMPLETE if needed */
		lock.lock();
		
		int stallP = p.getAssignedStall();
		freeStalls[stallP] = true;
		stall.signal();
		
		Gender genderP = p.getGender();
		if (genderP == Gender.MAN) menInAnteroom--;
		else womenInAnteroom--;
		
		if (menInAnteroom == 0 && womenInAnteroom == 0) {
            anteroom.signal();
        }

		injectTrace("<** LEAVING stall [" + p.getAssignedStall() + "] " + p);

		/* COMPLETE if needed */
		lock.unlock();
	}
}





