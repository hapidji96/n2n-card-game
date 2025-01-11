package hapidji.cardgame.model;

public class PlayerCardCharacterHighestCounts {
    private String playerName;
    private String cardAlphanumeric;
    private int maxCount;

    public PlayerCardCharacterHighestCounts(String playerName, String cardAlphanumeric, int maxCount) {
        this.playerName = playerName;
        this.cardAlphanumeric = cardAlphanumeric;
        this.maxCount = maxCount;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getCardAlphanumeric() {
        return cardAlphanumeric;
    }

    public void setCardAlphanumeric(String cardAlphanumeric) {
        this.cardAlphanumeric = cardAlphanumeric;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }
}
