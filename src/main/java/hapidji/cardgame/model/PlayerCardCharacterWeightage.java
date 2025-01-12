package hapidji.cardgame.model;

public class PlayerCardCharacterWeightage {
    private String playerName;
    private String character;
    private int characterWeightage;

    public PlayerCardCharacterWeightage(String playerName, String character, int characterWeightage) {
        this.playerName = playerName;
        this.character = character;
        this.characterWeightage = characterWeightage;
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

    public int getCharacterWeightage() {
        return characterWeightage;
    }

    public void setCharacterWeightage(int characterWeightage) {
        this.characterWeightage = characterWeightage;
    }

    @Override
    public String toString() {
        return "PlayerCardCharacterWeightage{" +
                "playerName='" + playerName + '\'' +
                ", character='" + character + '\'' +
                ", characterWeightage=" + characterWeightage +
                '}';
    }
}
