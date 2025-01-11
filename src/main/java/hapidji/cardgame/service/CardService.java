package hapidji.cardgame.service;

import hapidji.cardgame.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CardService {
    private CharacterWeightageService characterWeightageService;

    @Autowired
    public void setCharacterWeightageService(CharacterWeightageService characterWeightageService) {
        this.characterWeightageService = characterWeightageService;
    }

    public List<Card> getCardList() {
        List<Card> cards = List.of(
                new Card("2@"),
                new Card("2#"),
                new Card("2^"),
                new Card("2*"),
                new Card("3@"),
                new Card("3#"),
                new Card("3^"),
                new Card("3*"),
                new Card("4@"),
                new Card("4#"),
                new Card("4^"),
                new Card("4*"),
                new Card("5@"),
                new Card("5#"),
                new Card("5^"),
                new Card("5*"),
                new Card("6@"),
                new Card("6#"),
                new Card("6^"),
                new Card("6*"),
                new Card("7@"),
                new Card("7#"),
                new Card("7^"),
                new Card("7*"),
                new Card("8@"),
                new Card("8#"),
                new Card("8^"),
                new Card("8*"),
                new Card("9@"),
                new Card("9#"),
                new Card("9^"),
                new Card("9*"),
                new Card("10@"),
                new Card("10#"),
                new Card("10^"),
                new Card("10*"),
                new Card("J@"),
                new Card("J#"),
                new Card("J^"),
                new Card("J*"),
                new Card("Q@"),
                new Card("Q#"),
                new Card("Q^"),
                new Card("Q*"),
                new Card("K@"),
                new Card("K#"),
                new Card("K^"),
                new Card("K*"),
                new Card("A@"),
                new Card("A#"),
                new Card("A^"),
                new Card("A*")
        );

        return cards;
    }

    public List<Card> shuffleCards() throws Exception {
        try {
            List<Card> cards = new ArrayList<>(getCardList());
            Collections.shuffle(cards);
            return cards;
        } catch (Exception exception) {
            System.out.printf("Exception: %s\n", exception.getMessage());
        }

        return new ArrayList<>();
    }

    public List<List<Card>> partitionList(List<Card> list, int size) {
        List<List<Card>> partitions = new ArrayList<>();
        for (int i = 0; i < list.size(); i += size) {
            partitions.add(list.subList(i, Math.min(i + size, list.size())));
        }
        return partitions;
    }

    public List<PlayerCard> distributeCardsToPlayers(List<Card> cards) {
        List<PlayerCard> playerCards = new ArrayList<>();
        int maxPlayers = 4;
        int counter = 1;
        String playerName = "";
        for (Card card : cards) {
            playerName = "Player" + counter;
            playerCards.add(new PlayerCard(playerName, card.getName(), 0));
            counter++;
            if(counter == maxPlayers+1) {
                counter = 1;
            }
        }

        return playerCards;
    }

    public Winner checkWinnerRule1(List<PlayerCard> playerCards) {

        return new Winner("", false);
    }

    public Winner checkWinnerRule2(List<PlayerCard> playerCards) {
        return null;
    }

    public Winner calculatingWinner(List<PlayerCard> playerCards) throws Exception {
        try {
            //region Rule 1 : Players with highest number of cards with same alphanumeric
            Winner winner = new Winner();
            // Get distinct cards prefix of players
            List<PlayerCardCharacterCount> playerCardPrefixCounts = new ArrayList<>();
            for (PlayerCard playerCard : playerCards) {
                String cardPrefix = "";
                if (playerCard.getCardName().charAt(0) == '1') {
                    cardPrefix = "10";
                } else {
                    cardPrefix = String.valueOf(playerCard.getCardName().charAt(0));
                }
                String finalCardPrefix = cardPrefix;

                Optional<PlayerCardCharacterCount> playerCardPrefix = playerCardPrefixCounts.stream()
                        .filter(p -> p.getPlayerName().equalsIgnoreCase(playerCard.getPlayerName())
                                && p.getCharacter().equalsIgnoreCase(finalCardPrefix))
                        .findFirst();
                if(playerCardPrefix.isPresent()) {
                    playerCardPrefixCounts.stream()
                            .filter(p -> p.getPlayerName().equalsIgnoreCase(playerCard.getPlayerName())
                                    && p.getCharacter().equalsIgnoreCase(finalCardPrefix))
                            .findFirst()
                            .ifPresent(p -> p.setCount(p.getCount() + 1));
                } else {
                    playerCardPrefixCounts.add(new PlayerCardCharacterCount(playerCard.getPlayerName(), finalCardPrefix, 1));
                }
            }

            // Get player, prefix with highest count
            int maxCount = playerCardPrefixCounts.stream()
                    .mapToInt(PlayerCardCharacterCount::getCount)
                    .max()
                    .orElse(0);
            List<PlayerCardCharacterCount> playerPrefixHighestCount = playerCardPrefixCounts.stream()
                    .filter(p -> p.getCount() == maxCount)
                    .toList();

            // Todo : the output seems inaccurate, please check this section
            for (PlayerCardCharacterCount playerCardCharacterCount : playerPrefixHighestCount) {
                PlayerCardCharacterCount item = new PlayerCardCharacterCount(playerCardCharacterCount.getPlayerName(), playerCardCharacterCount.getCharacter(), playerCardCharacterCount.getCount());
                System.out.println(item.toString());
            }

            if(playerPrefixHighestCount.size() == 1) {
                // Winner found
                return new Winner(playerPrefixHighestCount.get(0).getPlayerName(), true);
            }
            //endregion

            // Rule 2 : If more than 1 player has the same number of winning cards, the alphanumeric part with higher value won.
            List<PlayerCardCharacterWeightage> playerCardCharacterWeightages = new ArrayList<>();
            if(playerPrefixHighestCount.size() > 1) {
                for (PlayerCardCharacterCount item : playerCardPrefixCounts) {
                    playerCardCharacterWeightages.add(new PlayerCardCharacterWeightage(item.getPlayerName(), item.getCharacter(), characterWeightageService.getCharWeightage(item.getCharacter(), "prefix")));
                }
            }

            int highestWeightage = playerCardCharacterWeightages.stream()
                    .mapToInt(PlayerCardCharacterWeightage::getCharacterWeightage)
                    .max()
                    .orElse(0);
            List<PlayerCardCharacterWeightage> playerCardCharacterWeightagesHighest = playerCardCharacterWeightages.stream()
                    .filter(p -> p.getCharacterWeightage() == highestWeightage)
                    .toList();

            if(playerCardCharacterWeightagesHighest.size() == 1) {
                return new Winner(playerCardCharacterWeightagesHighest.get(0).getPlayerName(), true);
            }

//            if(playerCardCharacterWeightagesHighest.size() > 1) {
//                List<PlayerCard> playerCardHighestGroup = new ArrayList<>();
//                for (PlayerCardCharacterWeightage item : playerCardCharacterWeightagesHighest) {
//                    List<PlayerCard> tempList = new ArrayList<>();
//                    tempList = playerCards.stream()
//                            .filter(playerCard -> playerCard.getPlayerName().equalsIgnoreCase(item.getPlayerName())
//                                    && String.valueOf(playerCard.getCardName().charAt(0)).equalsIgnoreCase(item.getCharacter())).toList();
//
//                    for (PlayerCard playerCard : tempList) {
//                        playerCardHighestGroup.add(playerCard);
//                    }
//                }
//            }



            // Get distinct player names, add into score list and initialize the score as 0
//        String[] players = playerCards.stream().map(PlayerCard::getPlayerName).distinct().toArray(String[]::new);
//        List<Score> scores = new ArrayList<>();
//        for (String name : players) {
//            scores.add(new Score(name, 0));
//        }
//
//        // Calculate players total score
//        for (String name : players) {
//            for (PlayerCard playerCard : playerCards) {
//                if(playerCard.getPlayerName().equalsIgnoreCase(name)) {
//                    scores.stream()
//                            .filter(score -> score.getPlayerName().equalsIgnoreCase(name))
//                            .findFirst()
//                            .ifPresent(score -> score.setScore(score.getScore() + getCardWeightage(playerCard.getCardName())));
//
//                }
//            }
//        }
        } catch (Exception exception) {
            System.out.printf("Exception: %s\n", exception.getMessage());
        }

        return new Winner("", false);
    }
}
