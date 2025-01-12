package hapidji.cardgame.model;

public class PlayerCard {
    private String playerName;
    private String cardName;

    public PlayerCard(String playerName, String cardName) {
        this.playerName = playerName;
        this.cardName = cardName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    @Override
    public String toString() {
        return "PlayerCard{" +
                "playerName='" + playerName + '\'' +
                ", cardName='" + cardName + '\'' +
                '}';
    }
}
