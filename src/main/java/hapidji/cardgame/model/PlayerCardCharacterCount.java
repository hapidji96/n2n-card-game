package hapidji.cardgame.model;

public class PlayerCardCharacterCount {
    private String playerName;
    private String character;
    private int count;

    public PlayerCardCharacterCount() {
    }

    public PlayerCardCharacterCount(String playerName, String character, int count) {
        this.playerName = playerName;
        this.character = character;
        this.count = count;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "PlayerCardCharacterCount{" +
                "playerName='" + playerName + '\'' +
                ", character='" + character + '\'' +
                ", count=" + count +
                '}';
    }
}
