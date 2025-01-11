package hapidji.cardgame.model;

public class PlayerCard {
    private String playerName;
    private String cardName;
    private int cardWeightage;

    public PlayerCard(String playerName, String cardName, Integer cardWeightage) {
        this.playerName = playerName;
        this.cardName = cardName;
        this.cardWeightage = cardWeightage;
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

    public Integer getCardWeightage() {
        return cardWeightage;
    }

    public void setCardWeightage(Integer cardWeightage) {
        this.cardWeightage = cardWeightage;
    }
}
