package hapidji.cardgame.model;

public class Winner {
    private String playerName;
    private boolean isWinner;

    public Winner(String playerName, boolean isWinner) {
        this.playerName = playerName;
        this.isWinner = isWinner;
    }

    public Winner() {

    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }
}
