package p03_RunnableExample;

// instead of extending Thread just implement Runnable.
// Interface Runnable requires the implementation of a run method
public class RunnablePrimeCalculator implements Runnable {

	private int limit;
	private String name;
    
	public RunnablePrimeCalculator (String name, int limit) {
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
