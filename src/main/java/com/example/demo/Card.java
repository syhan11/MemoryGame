package com.example.demo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String frontcard;
    private String backcard;
    private int cardval;
    private int cardposition;
    private boolean found;   // true is if matching card has been found
    private boolean front;  // true if card is facing front

    public Card() {
        this.frontcard = null;
        this.backcard = null;
        this.cardval = 0;
        this.cardposition = -1;
        this.found = false;
        this.front = false;
    }

    public Card(String frontcard, String backcard) {
        this.frontcard = frontcard;
        this.backcard = backcard;
        this.cardval = 0;
        this.cardposition = -1;
        this.found = false;
        this.front = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFrontcard() {
        return frontcard;
    }

    public void setFrontcard(String frontcard) {
        this.frontcard = frontcard;
    }

    public String getBackcard() {
        return backcard;
    }

    public void setBackcard(String backcard) {
        this.backcard = backcard;
    }

    public int getCardval() {
        return cardval;
    }

    public void setCardval(int cardval) {
        this.cardval = cardval;
    }

    public int getCardposition() {
        return cardposition;
    }

    public void setCardposition(int cardposition) {
        this.cardposition = cardposition;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public boolean isFront() {
        return front;
    }

    public void setFront(boolean front) {
        this.front = front;
    }
}
