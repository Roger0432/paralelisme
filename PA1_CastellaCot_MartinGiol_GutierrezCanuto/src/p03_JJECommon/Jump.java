package p03_JJECommon;

public class Jump implements Runnable {
	
	private Synchronizer sync;
	private int id;
	
	public Jump (int id, Synchronizer sync) {
		this.sync = sync;
		this.id = id;
	}
	
	public void run () {
		while (true) {
			sync.letMeJump(id);
			sync.writeString("JUMP("+id+")-");
			sync.jumpDone(id);
		}
	}
}

