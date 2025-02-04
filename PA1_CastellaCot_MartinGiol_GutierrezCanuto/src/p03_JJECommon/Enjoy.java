package p03_JJECommon;

public class Enjoy implements Runnable {
	private Synchronizer sync;
	private int id;
	
	public Enjoy (int id, Synchronizer sync) {
		this.sync = sync;
		this.id = id;
	}
	
	public void run () {
		while (true) {
			if (sync.letMeEnjoy(id)) 
				sync.writeString("ENJOY("+id+")\n");
			else 
				sync.writeString("JOY("+id+")\n");
			sync.enjoyDone(id);
		}
	}
}

