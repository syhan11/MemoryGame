package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    final int MAX = 4;
    final String backimg = "https://res.cloudinary.com/dpvaq7u3d/image/upload/v1564854258/hzktq8uc9zovh7atkg0x.jpg";
    private int sz;
    private Card cards[][];

    public Deck(int sz) {
        this.sz = sz;
        this.cards = new Card[sz][sz];

        for (int i = 0; i < sz; i++) {
            for (int j = 0; j < sz; j++) {
                this.cards[i][j] = new Card();
                this.cards[i][j].setBackcard(backimg);
                this.cards[i][j].setFound(false);
            }
        }
    }




    public int getSz(){
        return this.sz;
    }


    public Card[][] getCards() {
        return this.cards;
    }

    /************
     public void setCards(Card[][] cards) {
     this.cards = cards;
     }
     ***************/


    public Card[] getRow(int index) {
        return this.cards[index];
    }

    public Card getCard(int row, int col) {
        return this.cards[row][col];
    }

    public void setCard(int row, int col, Card card) {
        cards[row][col] = card;
    }

    public void shuffleCards () {
        String[] imgArray = {"https://res.cloudinary.com/dpvaq7u3d/image/upload/v1562718078/20180907_DSC_0619.jpg",
                "https://res.cloudinary.com/dpvaq7u3d/image/upload/v1562718219/20180410_DSC_1054-1.jpg",
                "https://res.cloudinary.com/dpvaq7u3d/image/upload/v1562718010/20180721_DSC_0403.jpg",
                "https://res.cloudinary.com/dpvaq7u3d/image/upload/v1562717164/DSC_0092.jpg",
                "https://res.cloudinary.com/dpvaq7u3d/image/upload/v1562718118/20180907_DSC_0763.jpg",
                "https://res.cloudinary.com/dpvaq7u3d/image/upload/v1565722204/vg4rkkvpo0paihmolgon.png",
                "https://res.cloudinary.com/dpvaq7u3d/image/upload/v1565721749/photo-1516632664305-eda5d6a5bb99.jpg",
                "https://res.cloudinary.com/dpvaq7u3d/image/upload/v1565721131/orange_highdefinition_picture_167223.jpg",
                "https://res.cloudinary.com/dpvaq7u3d/image/upload/v1562718078/20180907_DSC_0619.jpg",
                "https://res.cloudinary.com/dpvaq7u3d/image/upload/v1562718219/20180410_DSC_1054-1.jpg",
                "https://res.cloudinary.com/dpvaq7u3d/image/upload/v1562718010/20180721_DSC_0403.jpg",
                "https://res.cloudinary.com/dpvaq7u3d/image/upload/v1562717164/DSC_0092.jpg",
                "https://res.cloudinary.com/dpvaq7u3d/image/upload/v1562718118/20180907_DSC_0763.jpg",
                "https://res.cloudinary.com/dpvaq7u3d/image/upload/v1565722204/vg4rkkvpo0paihmolgon.png",
                "https://res.cloudinary.com/dpvaq7u3d/image/upload/v1565721749/photo-1516632664305-eda5d6a5bb99.jpg",
                "https://res.cloudinary.com/dpvaq7u3d/image/upload/v1565721131/orange_highdefinition_picture_167223.jpg"};


        ArrayList<Card> tmpAllCards = new ArrayList<Card>();
        ArrayList<Card> tmpAll = new ArrayList<Card>();
        Card tmpCard;
        int index = 0;

        for (int i = 0; i < MAX; i++){
            for (int j = 0; j < MAX; j++) {
                tmpCard = getCard(i, j);
                if (tmpCard != null) {

                    index = (i * 4) + j;
                    if (index >= 8)
                        tmpCard.setCardval((index-7));
                    else
                        tmpCard.setCardval(index+1);

                    tmpCard.setFrontcard(imgArray[index]);
                    tmpAll.add(tmpCard);
                    //cardRepository.save(tmpCard);
                }
            }
        }


        // shuffle cards
        Random randomN = new Random();
        int ranNo;
        int counter = 16;

        while (true) {
            ranNo = randomN.nextInt(tmpAll.size());

            tmpCard = tmpAll.get(ranNo);
            tmpAllCards.add(tmpCard);
            tmpAll.remove(ranNo);

            if (tmpAll.size() == 0)
                break;
        }

        for (int i = 0; i < MAX; i++ ){
            for (int j = 0; j < MAX; j++) {
                tmpCard = tmpAllCards.get(0);
                tmpCard.setCardposition((i*4)+ j +1);
                setCard(i, j, tmpCard);
                //cardRepository.save(tmpCard);
                tmpAllCards.remove(0);
            }
        }
    }

}
