public class Card {

    private int cardValue;
    
    public Card() {      
    }
	
	public Card(int cardValue) throws InvalidCardException {
		if (cardValue >= 1) {
			this.cardValue = cardValue;
		} else {
			throw new InvalidCardException();
		}
	}
	
	public int readValue() {
		return this.cardValue;
	}
}