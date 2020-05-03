package com.example.Bank;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankService {

    private final EntityManager entityManager;
    private static long mainId=0;



    @Transactional
    public User createUser(String firstName, String lastName, String login, String password,String number, String UniqueWord){
        User u=new User(firstName, lastName, login,  password, number,  UniqueWord);
        u.setId(mainId++);
        return  entityManager.merge(u);
    }
    @Transactional
    public User createUser(User u){
        u.setId(mainId++);
       return entityManager.merge(u);
    }
    public List<User> getAllUsers(){
        return entityManager.createQuery("from User").getResultList();
    }
    @Transactional
    public User addUser(User u, long id){
        u.setId(id);
        return entityManager.merge(u);
    }
    @Transactional
    public long countUsers() {
        return entityManager.createQuery("SELECT COUNT(u) FROM User u", Long.class).getSingleResult();
    }
    @Transactional
    public void deleteUser(long place){
        entityManager.createQuery("delete from User u where u.id=:id").setParameter("id", place).executeUpdate();

    }
    @Transactional
    public User getUser(long place){
        return entityManager.find(User.class, place);
    }
    public long getPlace(User u){
       for(long i=0; i<this.countUsers(); i++){
           if(this.getUser(i).equals(u)) return i;
       }
       return -1;
    }
    @Transactional
    public User addCard(User u){
        if(u.getAmountCard()<=0)
            return u;
       deleteUser(getPlace(u));
        entityManager.clear();
       entityManager.flush();

       u.addCard();
       entityManager.merge(u);
       return u;


    }
    @Transactional
    public boolean addMoney(long number, long sum){
        long place=0;
        for(long i=0; i<this.countUsers(); i++){
            int j=this.getUser(i).getNum(number);
            if(j>=0){
                User u=this.getUser(i);
                u.cards.get(j).addMoney(sum);
                deleteUser(getPlace(u));
                entityManager.clear();
                entityManager.flush();
                entityManager.merge(u);
                return true;

            }

        }
        return false;
    }
    /*@Transactional
    public User addCard(User u){

       entityManager.set(entityManager.indexOf(u), u.addCard());

        return u;
    }
    public boolean addMoney(long number, long sum){
        int j=0;
        for(int i=0; i<entityManager.size(); i++){
            j=entityManager.get(i).getNum(number);
            if(j>=0){
                entityManager.get(i).cards.get(j).addMoney(sum);
                return true;
            }
        }
        return false;
    }*/





}
