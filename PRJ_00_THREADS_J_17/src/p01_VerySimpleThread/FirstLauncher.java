package p01_VerySimpleThread;

public class FirstLauncher {
    
    public static void main (String [] args) {
        PrimeCalculator mth;
        
        System.out.println("Main starts here");
        System.out.println("\nAvailable processors: "+
                           Runtime.getRuntime().availableProcessors());
        
        System.out.println();
        
        mth = new PrimeCalculator(10000);
        mth.start();
        
        System.out.println("Main ends here\n");
        
    }
    
}
