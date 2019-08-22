package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class HomeController {

    @Autowired
    CardRepository cardRepository;

    Deck deckofcard = new Deck(4);


    public ArrayList<Card> convertToArray(Deck carddeck) {
        ArrayList<Card> cards = new ArrayList<Card>();

        for (int i = 0; i < carddeck.getSz(); i++) {
            for (int j = 0; j < carddeck.getSz(); j++) {
                cards.add(carddeck.getCard(i, j));
            }
        }

        return cards;
    }

    @RequestMapping("/")
    public String homepage(Model model) {
        ArrayList<Card> cards;
        String msg = "Select a card.";

        deckofcard.shuffleCards();
        cards = convertToArray(deckofcard);
        model.addAttribute("allcards", cards);
        model.addAttribute("msg", msg);

        for (Card tmpcard : cards) {
            cardRepository.save(tmpcard);
        }

        model.addAttribute("disableflag", false);
        return "showcards";
    }

    @RequestMapping("/continue")
    public String continueGame(Model model) {
        ArrayList<Card> cards = cardRepository.findAllByFrontEqualsAndFoundEquals(true, false);
        for (Card curcard : cards) {
            curcard.setFront(false);
            cardRepository.save(curcard);
        }

        cards = cardRepository.findAll();
        model.addAttribute("allcards", cards);
        model.addAttribute("msg", "Select a new card");
        model.addAttribute("disableflag", false);
        return "showcards";
    }

    @RequestMapping ("/selectcard/{id}")
    public String selectCard (@PathVariable("id") String id, Model model) {
        int index = Integer.valueOf(id);
        Card curcard = cardRepository.findCardByCardpositionEquals(index);
        Card prevcard = cardRepository.findCardByFrontEqualsAndFoundEquals(true, false);
        String msg;

        if (curcard != null) {
            if (prevcard == null) {
                // first card selection - flip the card to face front
                curcard.setFront(true);
                curcard.setFound(false);
                cardRepository.save(curcard);
                msg = "Select next card.";
                model.addAttribute("disableflag", false);

            }
            else {
                // 2nd card selection
                // check for matching card
                if (prevcard.getCardval() == curcard.getCardval()) {
                    curcard.setFront(true);
                    curcard.setFound(true);
                    prevcard.setFound(true);
                    cardRepository.save(curcard);
                    cardRepository.save(prevcard);
                    msg = "Good work!!! Match made. Select a new card.";
                    model.addAttribute("disableflag", false);
                }
                else {
                    // unmatched
                    curcard.setFront(true);
                    curcard.setFound(false);
                    prevcard.setFront(true);
                    prevcard.setFound(false);
                    cardRepository.save(curcard);
                    cardRepository.save(prevcard);
                    msg = "Sorry but no match. Select 'Continue'.";
                    model.addAttribute("disableflag", true);
                }
            }
            model.addAttribute("msg", msg);
        }

        ArrayList<Card> cards;
        cards = cardRepository.findAll();
        model.addAttribute("allcards", cards);

        return "showcards";
    }

}