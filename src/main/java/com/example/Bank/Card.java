package com.example.Bank;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "cardnum")
    private long cardnum=(long) Math.abs(Math.random()*Math.pow(10, 16));
    @Column(name = "money")
    private long money=0;

    public long getId() {
        return id;
    }

    public long getCardnum() {
        return cardnum;
    }

    public long getMoney() {
        return money;
    }
    public String toString(){
        return this.cardnum+" "+ this.money;
    }
}
