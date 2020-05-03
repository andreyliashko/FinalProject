package com.example.Bank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "card")
public class Card {
    private static int _id=10;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "cardnum")
    private long cardnum=(long)(Math.random()*Math.pow(10, 16));

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
    public long addMoney(long money){
        if(this.money+money>=0)
            this.money+=money;
        return this.money;
    }
    public String toString(){
        return this.cardnum+" "+ this.money;
    }
}
