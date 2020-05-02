package com.example.Bank;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankService {

    private final List<User> entityManager;



    @Transactional
    public User createUser(String firstName, String lastName, String login, String password,String number, String UniqueWord){

        entityManager.add(new User(firstName, lastName, login,  password, number,  UniqueWord));
        return entityManager.get(entityManager.size()-1);
    }
    @Transactional
    public User createUser(User u){
       entityManager.add(u);
       return u;
    }
    public List<User> getAllUsers(){
        return entityManager;
    }
    @Transactional
    public User addCard(User u){

       entityManager.remove(u);

       u.addCard();
       entityManager.add(u);
        return u;
    }





}
