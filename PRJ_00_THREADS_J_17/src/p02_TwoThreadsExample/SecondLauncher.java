package p02_TwoThreadsExample;


public class SecondLauncher {
    
    public static void main (String [] args) {
        NamedPrimeCalculator mthA, mthB;
        
        System.out.println("Main starts here");
        System.out.println("\nAvailable processors: "+
                           Runtime.getRuntime().availableProcessors());
        System.out.println();
        
        mthA = new NamedPrimeCalculator("     A",10000000);
        mthB = new NamedPrimeCalculator("B ",10000000);
        
        mthA.start();
        mthB.start();
        
        System.out.println("Main ends here\n");
        
    }
}
