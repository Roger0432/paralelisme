package p05_SleepSuspendResumeStop;

import java.awt.EventQueue;
import javax.swing.JTextArea;

public class DisplayPrimeCalculator extends Thread {
	// when run, compute prime numbers up to limit

	private int limit;
	private JTextArea display; // these threads have a display of their own
	private long timeToSleep; // these threads may be put to sleep

	public DisplayPrimeCalculator(JTextArea display, int limit) {
		this.limit = limit;
		this.display = display;
		this.timeToSleep = 0;
	}

	public void setTimeToSleep(long ts) {
		this.timeToSleep = ts;
	}

	@Override
	public void run() {
		for (int i = 2; i <= limit; i++) {
			// first check if it's time to go to sleep
			if (this.timeToSleep > 0) {
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						display.append(" going to sleep...\n");
					}
				});
				
				try {
					Thread.sleep(timeToSleep);
				} catch (InterruptedException ie) {}
				
				this.timeToSleep = 0;
			}
			if (isPrime(i)) {
				final int val = i;
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						display.append(val + "\n");
						// force scroll down so that movement is apparent
						display.setCaretPosition(display.getDocument().getLength());
					}
				});
			}
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				display.append("  DONE !!!");
			}
		});
	}

	private boolean isPrime(int num) {
		for (int factor = 2; factor <= num / 2; factor++) {
			if (num % factor == 0) {
				return false;
			}
		}
		return true;
	}
}
