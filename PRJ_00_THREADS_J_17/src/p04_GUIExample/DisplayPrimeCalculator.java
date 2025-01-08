package p04_GUIExample;

import java.awt.*;
import javax.swing.JTextArea;
import java.time.Duration;
import java.time.Instant;

public class DisplayPrimeCalculator extends Thread {
	// when run, compute prime numbers up to limit

	private int limit;
	private JTextArea display; // these threads have a display of their own

	public DisplayPrimeCalculator(JTextArea display, int limit) {
		this.limit = limit;
		this.display = display;
	}

	@Override
	public void run() {
		Instant start = Instant.now();
		for (int i = 2; i <= limit; i++) {
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
		Instant end = Instant.now();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				display.append("  DONE !!!\n");
				display.append("Time (aprox): "+Duration.between(start, end).toMillis()+" ms");
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
