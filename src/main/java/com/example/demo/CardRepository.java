package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface CardRepository extends CrudRepository<Card, Long> {
    ArrayList<Card> findAll();
    ArrayList<Card> findAllByOrderByCardposition();
    Card findCardByCardpositionEquals(int index);
    Card findCardByFrontEqualsAndFoundEquals(boolean front, boolean found);
    ArrayList<Card> findAllByFrontEqualsAndFoundEquals(boolean front, boolean found);
    long countCardsByFoundEquals(boolean found);
}
