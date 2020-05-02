package com.example.Bank;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity

@Table(name = "user")
@NoArgsConstructor
public class User {
    private  int amountCard=3;

    User(String firstName, String lastName, String login, String password,String number, String UniqueWord){
        this.firstName=firstName;
        this.lastName=lastName;
        this.login=login;
        this.password=password;
        this.number=number;
        this.uniqueWord=UniqueWord;
    }

    public boolean addCard(){
        if(--amountCard<0) return false;
        cards.add(new Card());
        return true;
    }


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Card> cards=new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;
    @Column(name = "log")
    private String login;
    @Column(name = "pass")
    private String password;
    @Column(name = "number")
    String number;
    @Column(name="uniqueword")
    String uniqueWord;


    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getNumber() {
        return number;
    }
    public String toString(){
        return this.getFirstName()+" "+this.getLastName()+" "+cards;
    }

    public String getUniqueWord() {
        return uniqueWord;
    }
}

