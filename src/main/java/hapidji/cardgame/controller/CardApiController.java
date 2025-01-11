package hapidji.cardgame.controller;

import hapidji.cardgame.model.Card;
import hapidji.cardgame.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardApiController {
    private CardService cardService;

    @Autowired
    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    @RequestMapping(value = "/get-cards", method = RequestMethod.GET)
    public List<Card> getCards() {
        return cardService.getCardList();
    }
}
