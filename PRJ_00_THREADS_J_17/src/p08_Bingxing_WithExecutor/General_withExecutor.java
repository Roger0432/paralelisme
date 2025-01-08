package p08_Bingxing_WithExecutor;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.*;

public class General_withExecutor {

    private static final long theNumber = 368788194383L;
    private static final int workload = 10000; // number of candidates each
                                               // soldier can process
    public static void main (String [] args) {
        int limit;
        int requiredSoldiers;
        boolean candidates [];
        Soldier theArmy[];
        
        System.out.println("\nAvailable processors: "+Runtime.getRuntime().availableProcessors()+"\n");
        
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
        
       // army has been created. Now let's use an executor...
        

       ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
       Instant start = Instant.now(); 
       for (Soldier s : theArmy) {
    	   executor.submit(s);
       }
       
       executor.shutdown();
       while (!executor.isTerminated()) {/* do nothing */} 
       Instant end = Instant.now();
       
       // wait until all soldiers have finished working 
        
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
    
 
    
}
