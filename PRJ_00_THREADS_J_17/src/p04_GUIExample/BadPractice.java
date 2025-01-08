package p04_GUIExample;

import javax.swing.JTextArea;

public class BadPractice extends Thread{
    // when run, compute prime numbers up to limit
    
    private int limit;
    private JTextArea display; // these threads have a display of their own 
    
    public BadPractice (JTextArea display, int limit) {
        this.limit = limit;
        this.display = display;
    }
    
    @Override
    public void run () {
        for (int i=2; i<=limit; i++) {
            if (isPrime(i)) {
                this.display.append(i+"\n");
                // force scroll down so that movement is apparent
                this.display.setCaretPosition(
                                     this.display.getDocument().getLength());
            }
        }
        this.display.append("  DONE !!!");
    }
    
    private boolean isPrime (int num) {
        for (int factor=2; factor<=num/2; factor++) {
            if (num%factor==0) {return false;}
        }
        return true;
    }
}
