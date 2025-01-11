package hapidji.cardgame.model;

public class PlayerCardAlphanumericCount {
    private String playerName;
    private char cardAlphanumeric;
    private int count;

    public PlayerCardAlphanumericCount(String playerName, char cardAlphanumeric, int count) {
        this.playerName = playerName;
        this.cardAlphanumeric = cardAlphanumeric;
        this.count = count;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public char getCardAlphanumeric() {
        return cardAlphanumeric;
    }

    public void setCardAlphanumeric(char cardAlphanumeric) {
        this.cardAlphanumeric = cardAlphanumeric;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
