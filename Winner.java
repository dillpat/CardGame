
public class Winner {

	private int winnerId;
	
	public Winner() {
		this.winnerId = 0;
	}

	public synchronized void setWinnerId(int winnerId) {
		this.winnerId = winnerId;
	}
	
	public synchronized int getWinnerId() {
		return this.winnerId;
	}
}
