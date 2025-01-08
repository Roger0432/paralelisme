package p01_VerySimpleThread;


public class PrimeCalculator extends Thread{
    // when run, compute prime numbers up to limit
    
    private int limit;
    
    public PrimeCalculator (int limit) {
        this.limit = limit;
    }
    
    @Override
    public void run () {
        for (int i=2; i<=limit; i++) {
            if (isPrime(i)) {
                System.out.print(i+" ");
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
