package hapidji.cardgame.model;

public class Winner {
    private String playerName;
    private boolean isWinner;
    private String reason;
    private StringBuilder logs;

    public Winner(String playerName, boolean isWinner, String reason, StringBuilder logs) {
        this.playerName = playerName;
        this.isWinner = isWinner;
        this.reason = reason;
        this.logs = logs;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public StringBuilder getLogs() {
        return logs;
    }

    public void setLogs(StringBuilder logs) {
        this.logs = logs;
    }
}
