package hapidji.cardgame.model;

import java.util.List;

public class CardWeightage extends Card{
    private long weightages;

    public CardWeightage(String name, long weightages) {
        super(name);
        this.weightages = weightages;
    }

    public long getWeightages() {
        return weightages;
    }

    public void setWeightages(long weightages) {
        this.weightages = weightages;
    }

    public List<CardWeightage> getCardWeightages() {
        List<CardWeightage> cardWeightages = List.of(
                new CardWeightage("2@", 10),
                new CardWeightage("2#", 11),
                new CardWeightage("2^", 12),
                new CardWeightage("2*", 13),
                new CardWeightage("3@", 200),
                new CardWeightage("3#", 210),
                new CardWeightage("3^", 220),
                new CardWeightage("3*", 230),
                new CardWeightage("4@", 3000),
                new CardWeightage("4#", 3100),
                new CardWeightage("4^", 3200),
                new CardWeightage("4*", 3300),
                new CardWeightage("5@", 41000),
                new CardWeightage("5#", 42000),
                new CardWeightage("5^", 43000),
                new CardWeightage("5*", 44000),
                new CardWeightage("6@", 500000),
                new CardWeightage("6#", 510000),
                new CardWeightage("6^", 520000),
                new CardWeightage("6*", 530000),
                new CardWeightage("7@", 6000000),
                new CardWeightage("7#", 6100000),
                new CardWeightage("7^", 6200000),
                new CardWeightage("7*", 6300000),
                new CardWeightage("8@", 7000000),
                new CardWeightage("8#", 71000000),
                new CardWeightage("8^", 72000000),
                new CardWeightage("8*", 73000000),
                new CardWeightage("9@", 800000000),
                new CardWeightage("9#", 810000000),
                new CardWeightage("9^", 820000000),
                new CardWeightage("9*", 830000000),
                new CardWeightage("10@", 830000000),
                new CardWeightage("10#", 101),
                new CardWeightage("10^", 102),
                new CardWeightage("10*", 103),
                new CardWeightage("J@", 110),
                new CardWeightage("J#", 111),
                new CardWeightage("J^", 112),
                new CardWeightage("J*", 113),
                new CardWeightage("Q@", 120),
                new CardWeightage("Q#", 121),
                new CardWeightage("Q^", 122),
                new CardWeightage("Q*", 123),
                new CardWeightage("K@", 130),
                new CardWeightage("K#", 131),
                new CardWeightage("K^", 132),
                new CardWeightage("K*", 133),
                new CardWeightage("A@", 140),
                new CardWeightage("A#", 141),
                new CardWeightage("A^", 142),
                new CardWeightage("A*", 143)
        );
        
        return cardWeightages;
    }
}
