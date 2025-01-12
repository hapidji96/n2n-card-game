package hapidji.cardgame.service;

import hapidji.cardgame.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.midi.Soundbank;
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
            playerCards.add(new PlayerCard(playerName, card.getName()));
            counter++;
            if(counter == maxPlayers+1) {
                counter = 1;
            }
        }

        return playerCards;
    }

    public Winner checkWinnerRule2(List<PlayerCard> playerCards) {
        return null;
    }

    public Winner calculatingWinner(List<PlayerCard> playerCards) throws Exception {
        StringBuilder logs = new StringBuilder();
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
            int finalMaxCount1 = maxCount;
            List<PlayerCardCharacterCount> playerPrefixHighestCount = playerCardPrefixCounts.stream()
                    .filter(p -> p.getCount() == finalMaxCount1)
                    .toList();

            logs.append("\nPlayers with Highest Prefix Occurrence:");
            System.out.println("Players with Highest Prefix Occurrence:");
            for (PlayerCardCharacterCount playerCardCharacterCount : playerPrefixHighestCount) {
                PlayerCardCharacterCount item = new PlayerCardCharacterCount(playerCardCharacterCount.getPlayerName(), playerCardCharacterCount.getCharacter(), playerCardCharacterCount.getCount());
                logs.append("\n" + item.toString());
                System.out.println(item.toString());
            }

            if(playerPrefixHighestCount.size() == 1) {
                // Eg: Scenario
                // Player4, 5, 4 occurences

                // Winner found - Player 4
                String reason = "\n(Rule 1 - Highest prefix occurrence - " + playerPrefixHighestCount.get(0).getCharacter() + " occurred " + playerPrefixHighestCount.get(0).getCount() + " times)";
                logs.append("\n" + reason);
                return new Winner(playerPrefixHighestCount.get(0).getPlayerName(), true, reason, logs);
            }
            //endregion

            // Rule 2 : If more than 1 player has the same number of winning cards, the alphanumeric part with higher value won.
            List<PlayerCardCharacterWeightage> playerCardHighestFirstCharacterWeightages = new ArrayList<>();
            if(playerPrefixHighestCount.size() > 1) {
                // Check if playerPrefixHighestCount items are referring to 1 single player
                boolean allItemsSamePlayer = playerPrefixHighestCount.stream()
                        .map(PlayerCardCharacterCount::getPlayerName)
                        .distinct() // Get unique player names
                        .count() == 1; // Check if there's only one unique name

                if(allItemsSamePlayer) {
                    // Eg: Scenario
                    // Player1, Q, 4 occurences
                    // Player1, 3, 4 occurences

                    // Winner found - Player1
                    StringBuilder reason = new StringBuilder("(Rule 1 - Multiple highest prefix occurrence happened only in single player - ");
                    int counter = 1;
                    for (PlayerCardCharacterCount playerCardCharacterCount : playerPrefixHighestCount) {
                        reason.append(playerCardCharacterCount.getCharacter()).append(" occurred ").append(playerCardCharacterCount.getCount()).append(" times");
                        if(counter < playerPrefixHighestCount.size()) {
                            reason.append(" , ");
                        }
                        counter++;
                    }
                    reason.append(")");
                    logs.append("\n" + reason.toString());
                    return new Winner(playerPrefixHighestCount.get(0).getPlayerName(), true, reason.toString(), logs);
                }

                for (PlayerCardCharacterCount item : playerPrefixHighestCount) {
                    String cardPrefix = "";
                    if (item.getCharacter().equalsIgnoreCase("10")) {
                        cardPrefix = "10";
                    } else {
                        cardPrefix = String.valueOf(item.getCharacter());
                    }
                    String finalCardPrefix = cardPrefix;
                    playerCardHighestFirstCharacterWeightages.add(new PlayerCardCharacterWeightage(item.getPlayerName(), finalCardPrefix, characterWeightageService.getCharWeightage(finalCardPrefix, "prefix")));
                }
            }

            List<PlayerCardCharacterWeightage> playerCardHighestPrefixWeightageCleaned = new ArrayList<>();
            if(playerCardHighestFirstCharacterWeightages.size() > 1) {
                // Eg: Scenario 1
                // Player1, Q, 110 weightage
                // Player2, J, 100 weightage

                // Eg: Scenario 2
                // Player1, Q, 110 weightage
                // Player1, J, 100 weightage
                // Player2, Q, 110 weightage

                // Get player, prefix with highest weightage
                maxCount = playerCardHighestFirstCharacterWeightages.stream()
                        .mapToInt(PlayerCardCharacterWeightage::getCharacterWeightage)
                        .max()
                        .orElse(0);

                int finalMaxCount = maxCount;
                playerCardHighestPrefixWeightageCleaned = playerCardHighestFirstCharacterWeightages.stream()
                        .filter(p -> p.getCharacterWeightage() == finalMaxCount)
                        .toList();
                // Output: Scenario 1
                // Player1, Q, 110 weightage

                // Output Scenario 2
                // Player1, Q, 110 weightage
                // Player2, Q, 110 weightage

                logs.append("\n\nPlayers with Highest Prefix Occurrence and Highest Weightage :");
                System.out.println("Players with Highest Prefix Occurrence and Highest Weightage:");
                for (PlayerCardCharacterWeightage item : playerCardHighestPrefixWeightageCleaned) {
                    PlayerCardCharacterWeightage playerCardCharacterWeightage = new PlayerCardCharacterWeightage(item.getPlayerName(), item.getCharacter(), item.getCharacterWeightage());
                    logs.append("\n" + playerCardCharacterWeightage.toString());
                    System.out.println(playerCardCharacterWeightage.toString());
                }
            }

            if(playerCardHighestPrefixWeightageCleaned.size() == 1) {
                // Eg: Scenario
                // Player1, Q, 110 weightage

                // Winner found - Player1, Q, 110 weightage
                String reason = "\n(Rule 2 - Player with the highest prefix occurrence and highest weightage - " + playerCardHighestPrefixWeightageCleaned.get(0).getCharacter() + " with " + playerCardHighestPrefixWeightageCleaned.get(0).getCharacterWeightage() + " weightage)";
                logs.append("\n" + reason);
                return new Winner(playerCardHighestPrefixWeightageCleaned.get(0).getPlayerName(), true, reason, logs);
            }

            List<PlayerCard> playerCardsOfHighestPrefixOccurence = new ArrayList<>();
            // Rule 3 : If tie, the symbol part with high value won.
            if(playerCardHighestPrefixWeightageCleaned.size() > 1) {
                // Eg: Scenario
                // Player1, Q, 110 weightage
                // Player2, Q, 110 weightage

                for (PlayerCardCharacterWeightage item : playerCardHighestPrefixWeightageCleaned) {
                    List<PlayerCard> tempList = playerCards.stream()
                            .filter(playerCard -> playerCard.getPlayerName().equalsIgnoreCase(item.getPlayerName())
                                    && playerCard.getCardName().startsWith(item.getCharacter())).toList();
                    for (PlayerCard playerCard : tempList) {
                        playerCardsOfHighestPrefixOccurence.add(playerCard);
                    }
                }

                // Output : Result
                // Player1, Q@
                // Player1, Q#
                // Player2, Q^
                // Player2, Q*

                logs.append("\n\nPlayer Cards of Highest Prefix Occurrence: :");
                System.out.println("Player Cards of Highest Prefix Occurrence :");
                for (PlayerCard playerCard : playerCardsOfHighestPrefixOccurence) {
                    PlayerCard item = new PlayerCard(playerCard.getPlayerName(), playerCard.getCardName());
                    logs.append("\n" + item.toString());
                    System.out.println(item.toString());
                }
            }

            List<PlayerCardCharacterWeightage> playerCardsPostfixWeightage = new ArrayList<>();
            for (PlayerCard playerCard : playerCardsOfHighestPrefixOccurence) {
                String cardPostfix = String.valueOf(playerCard.getCardName().charAt(playerCard.getCardName().length() - 1));

                playerCardsPostfixWeightage.add(new PlayerCardCharacterWeightage(playerCard.getPlayerName(),
                        cardPostfix,
                        characterWeightageService.getCharWeightage(cardPostfix, "postfix")));
            }

            logs.append("\n\nPlayer Cards with Highest Prefix Occurrence & Postfix Weightage :");
            System.out.println("Player Cards with Highest Prefix Occurrence & Postfix Weightage :");
            for (PlayerCardCharacterWeightage item : playerCardsPostfixWeightage) {
                PlayerCardCharacterWeightage playerCardCharacterWeightage = new PlayerCardCharacterWeightage(item.getPlayerName(), item.getCharacter(), item.getCharacterWeightage());
                logs.append("\n" + playerCardCharacterWeightage.toString());
                System.out.println(playerCardCharacterWeightage.toString());
            }

            // Output : playerCardsPostfixWeightage
            // Player1, @, 10 weightage
            // Player1, #, 20 weightage
            // Player2, ^, 30 weightage
            // Player2, *, 40 weightage

            // Get player, postfix with highest weightage
            int highest = playerCardsPostfixWeightage.stream()
                    .mapToInt(PlayerCardCharacterWeightage::getCharacterWeightage)
                    .max()
                    .orElse(0);

//            int finalHighest = highest;
            List<PlayerCardCharacterWeightage> playerCardsPostfixWeightageHighest = playerCardsPostfixWeightage.stream()
                    .filter(p -> p.getCharacterWeightage() == highest)
                    .toList();

            logs.append("\n\nPlayer Cards with Highest Prefix Occurrence & Highest Postfix :");
            System.out.println("Player Cards with Highest Prefix Occurrence & Highest Postfix :");
            for (PlayerCardCharacterWeightage item : playerCardsPostfixWeightageHighest) {
                PlayerCardCharacterWeightage playerCardCharacterWeightage = new PlayerCardCharacterWeightage(item.getPlayerName(), item.getCharacter(), item.getCharacterWeightage());
                logs.append("\n" + playerCardCharacterWeightage.toString());
                System.out.println(playerCardCharacterWeightage.toString());
            }

            if(playerCardsPostfixWeightageHighest.size() == 1) {
                // Eg: Scenario
                // Player2, *, 40 weightage

                // Winner found - Player1
                String reason = "\n(Rule 3 - Player with the highest postfix char weightage - " + playerCardHighestPrefixWeightageCleaned.get(0).getCharacter() + " with " + playerCardHighestPrefixWeightageCleaned.get(0).getCharacterWeightage() + " weightage)";
                return new Winner(playerCardHighestPrefixWeightageCleaned.get(0).getPlayerName(), true, reason, logs);
            }
        } catch (Exception exception) {
            System.out.printf("Exception: %s\n", exception.getMessage());
        }

        return new Winner("", false, "Error", logs);
    }
}
