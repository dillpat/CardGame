package cardGames;

public class Card {

    private int cardValue;
    
    public Card() {      
    }
	
	public Card(int cardValue) {
		this.cardValue = cardValue;	
	}
	public int readValue () {
		return this.cardValue;
	}
}