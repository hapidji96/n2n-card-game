package hapidji.cardgame.service;

import hapidji.cardgame.model.CharacterWeightage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CharacterWeightageService {
    public int getCharWeightage(String character, String position) {
        if(position.equalsIgnoreCase("prefix")) {
            return getPrefixWeightage().stream()
                    .filter(item -> item.getCharacter().equalsIgnoreCase(character))
                    .map(CharacterWeightage::getCharWeightage)
                    .findFirst()
                    .orElse(-1);
        } else if(position.equalsIgnoreCase("postfix")) {
            return getPostfixWeightage().stream()
                    .filter(item -> item.getCharacter().equalsIgnoreCase(character))
                    .map(CharacterWeightage::getCharWeightage)
                    .findFirst()
                    .orElse(-1);
        }

        return -1;
    }

    public List<CharacterWeightage> getPrefixWeightage() {
        List<CharacterWeightage> prefixWeightages = new ArrayList<>();
        prefixWeightages.add(new CharacterWeightage("2", 10));
        prefixWeightages.add(new CharacterWeightage("3", 20));
        prefixWeightages.add(new CharacterWeightage("4", 30));
        prefixWeightages.add(new CharacterWeightage("5", 40));
        prefixWeightages.add(new CharacterWeightage("6", 50));
        prefixWeightages.add(new CharacterWeightage("7", 60));
        prefixWeightages.add(new CharacterWeightage("8", 70));
        prefixWeightages.add(new CharacterWeightage("9", 80));
        prefixWeightages.add(new CharacterWeightage("10", 90));
        prefixWeightages.add(new CharacterWeightage("J", 100));
        prefixWeightages.add(new CharacterWeightage("Q", 110));
        prefixWeightages.add(new CharacterWeightage("K", 120));
        prefixWeightages.add(new CharacterWeightage("A", 130));

        return prefixWeightages;
    }

    public List<CharacterWeightage> getPostfixWeightage() {
        List<CharacterWeightage> postfixWeightages = new ArrayList<>();
        postfixWeightages.add(new CharacterWeightage("@", 10));
        postfixWeightages.add(new CharacterWeightage("#", 20));
        postfixWeightages.add(new CharacterWeightage("^", 30));
        postfixWeightages.add(new CharacterWeightage("*", 40));
        return postfixWeightages;
    }
}
