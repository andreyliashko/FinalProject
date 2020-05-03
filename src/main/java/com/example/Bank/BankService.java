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
    public boolean createUser(String firstName, String lastName, String login, String password,String number, String UniqueWord){
        User u=new User(firstName, lastName, login,  password, number,  UniqueWord);
        if(this.getPlace(u)>=0)return false;
        u.setId(mainId++);
        entityManager.merge(u);
        return  true;
    }
    @Transactional
    public boolean createUser(User u){
        if(this.getPlace(u)>=0)return false;
        u.setId(mainId++);
        entityManager.merge(u);
       return true;
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
    @Transactional
    public boolean sendFromCardToCard(long number1, long number2, long sum){
        long i1=0;
        int j1=0;
        for(long i=0; i<this.countUsers(); i++){
            j1=this.getUser(i).getNum(number1);
            if(j1>=0){
                i1=i;
                i=this.countUsers();
            }
        }
        if(j1<0) return false;
        User u1=this.getUser(i1);
        if(u1.cards.get(j1).getMoney()-sum<0)
            return false;
        long i2=0;
        int j2=0;
        for(long i=0; i<this.countUsers(); i++){
            j2=this.getUser(i).getNum(number2);
            if(j2>=0){
                i2=i;
                i=this.countUsers();
            }
        }
        if(j2<0) return false;
        User u2=this.getUser(i2);
        deleteUser(i1);
        deleteUser(i2);
        entityManager.clear();
        entityManager.flush();
        u1.cards.get(j1).addMoney(-sum);
        u2.cards.get(j2).addMoney(sum);
        entityManager.merge(u1);
        entityManager.merge(u2);

        return true;
    }
    public long userEnter(String login, String password){
        for(long i=0; i<this.countUsers(); i++){
            if(this.getUser(i).thisLogAndPass(login, password)) return i;
        }
        return -1;
    }






}
