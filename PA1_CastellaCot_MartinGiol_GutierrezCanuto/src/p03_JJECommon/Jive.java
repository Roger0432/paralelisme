package p03_JJECommon;

public class Jive implements Runnable {
	private Synchronizer sync;
	private int id;
	
	public Jive (int id, Synchronizer sync) {
		this.sync = sync;
		this.id = id;
	}
	
	public void run () {
		while (true) {
			sync.letMeJive(id);
			sync.writeString("jive("+id+")-");
			sync.jiveDone(id);
		}
	}
}
