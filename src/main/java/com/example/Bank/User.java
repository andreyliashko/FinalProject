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
    public int getAmountCard(){
        return amountCard;
    }

    User(String firstName, String lastName, String login, String password,String number, String UniqueWord){
        this.firstName=firstName;
        this.lastName=lastName;
        this.login=login;
        this.password=password;
        this.number=number;
        this.uniqueWord=UniqueWord;
        this.id=id;
    }
    public Long setId(long id){
        this.id=id;
        return this.id;
    }

    public User addCard(){
        if(--amountCard>=0)
            cards.add(new Card());
        return this;
    }


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    protected List<Card> cards=new ArrayList<>();

    @Id
    private long id=0;

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
        return this.id+" "+this.getFirstName()+" "+this.getLastName()+" "+cards;
    }
    public boolean equals(User u){
        return this.firstName.equals(u.firstName)&&this.lastName.equals(u.lastName)&&this.login.equals(u.login)
                &&this.password.equals(u.password)&&this.uniqueWord.equals(u.uniqueWord);
    }



    public int getNum(long cardNum){
        for(int i=0; i<cards.size(); i++){
            if(cards.get(i).getCardnum()==cardNum) return i;
        }
        return -1;
    }

    public String getUniqueWord() {
        return uniqueWord;
    }
}

