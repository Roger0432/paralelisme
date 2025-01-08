package p08_Bingxing_WithExecutor;

public class Soldier implements Runnable {
    
    private int id; // soldier id-number
    private int first, last;
    private boolean candidates[];
    private long theNumber;
    
    public Soldier (int id, int first, int last, 
                    long theNumber, boolean candidates[]) {
        this.id = id;
        this.first = first;
        this.last = last;
        this.theNumber = theNumber;
        this.candidates = candidates;
    }
    
    @Override
    public void run () {
        System.out.println("Soldier "+id+" STARTS working");
        for(int i=first; i<=last; i++) {
            if (theNumber%(i+1)==0) {candidates[i]=true;}
            else {candidates[i]=false;}
        }
        System.out.println("Soldier "+id+" HAS FINISHED working");
    }   
}
