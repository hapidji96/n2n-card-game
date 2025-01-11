package hapidji.cardgame.model;

public class Card {
    private String name;
    private int weightage;

    public Card(String name, Integer weightage) {
        this.name = name;
        this.weightage = weightage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeightage() {
        return weightage;
    }

    public void setWeightage(Integer weightage) {
        this.weightage = weightage;
    }
}
