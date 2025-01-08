package p07_Bingxing;

import java.time.Duration;
import java.time.Instant;

public class General {

    private static final long theNumber = 368788194383L;
    private static final int workload = 10000; // number of candidates each
                                               // soldier can process
    public static void main (String [] args) {
        int limit;
        int requiredSoldiers;
        boolean candidates [];
        Soldier theArmy[];
        
        limit = (int)(Math.ceil(Math.sqrt(theNumber)));
        candidates = new boolean[limit];
        requiredSoldiers = limit/workload;
        if (requiredSoldiers*workload < limit) {requiredSoldiers++;}
        theArmy = new Soldier[requiredSoldiers];
        
        // create the soldiers
        for (int i=0; i<theArmy.length; i++) {
            int first, last;
            first = workload*i;
            last = workload*(i+1)-1;
            if (last>limit-1) {last = limit-1;}
            theArmy[i]=new Soldier(i,first, last, theNumber, candidates);
        }
        
        // start the soldiers
        Instant start = Instant.now(); 
        for (int i=0; i<theArmy.length; i++) {
            theArmy[i].start();
        }
        
        //wait for all soldiers to finish working
        while (thereAreSoldiersWorking(theArmy)) {/*do nothing */}
        
        /*  
        // this piece of code will also do the job 
        // and more efficiently, probably...
        for (Soldier s : theArmy) {
        	try {s.join();} catch(InterruptedException ie) {};
        }
        
        */
        Instant end = Instant.now();
        
        System.out.println("\nSoldiers did their job in: "+Duration.between(start, end).toMillis()+ "ms");
        // show the prime factors
        System.out.println("\n...And the prime factors are:");
        for (int i=1; i<candidates.length; i++) {
            if (candidates[i]) {
                if (isPrime(i+1)) {
                    System.out.println(i+1);
                }
            }
        }
    }
    
    private static boolean isPrime (int num) {
        for (int factor=2; factor<=num/2; factor++) {
            if (num%factor==0) {return false;}
        }
        return true;
    }
    
    private static boolean thereAreSoldiersWorking (Thread [] army) {
        for (int i=0; i<army.length; i++) {
            if (army[i].isAlive()) {return true;}
        }
        return false;
    }
    
}
