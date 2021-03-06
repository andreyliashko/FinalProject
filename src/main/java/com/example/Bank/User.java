package com.example.Bank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user")
public class User {
    private  int amountCard=3;
    private static long numCard=(long)(Math.random()*Math.pow(10,16));
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
    public boolean thisLogAndPass(String login, String password){
        return this.password.equals(password)&&this.login.equals(login);
    }
    public boolean alreadyExtinct(User u){
        return this.login.equals(u.login)||this.password.equals(u.password)||this.number.equals(u.number);
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
    public boolean containsCard(long card){
        for(int i=0; i<cards.size(); i++){
            if(cards.get(i).getCardnum()==card) return true;
        }
        return false;
    }



    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    protected List<Card> cards=new ArrayList<>();

    @Id
    private long id=0;

    @Column(name = "firstName")
    @NotNull
    private String firstName;

    @Column(name = "lastName")
    @NotNull
    private String lastName;


    @Column(name = "login")
    @NotNull
    private String login;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "number")
    @NotNull
    String number;
    @NotNull
    @Column(name="uniqueWord")
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

        return "Hello, "+this.getFirstName()+" "+this.getLastName()+"\n";
    }
    public String card1(){
           if(0<cards.size())
               return ""+cards.get(0);
        return "";

    }
    public String card2(){
        if(1<cards.size())
            return ""+cards.get(1);
        return "";

    }
    public String card3(){
        if(2<cards.size())
            return ""+cards.get(2);
        return "";

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

