package p02_TwoThreadsExample;


public class NamedPrimeCalculator extends Thread{
    // when run, compute prime numbers up to limit
    
    private int limit;
    private String name; // these threads have names 
    
    public NamedPrimeCalculator (String name, int limit) {
        this.limit = limit;
        this.name = name;
    }
    
    @Override
    public void run () {
        for (int i=2; i<=limit; i++) {
            if (isPrime(i)) {
                System.out.println(this.name+":"+i+" ");
            }
        }
    }
    
    private boolean isPrime (int num) {
        for (int factor=2; factor<=num/2; factor++) {
            if (num%factor==0) {return false;}
        }
        return true;
    }
}
