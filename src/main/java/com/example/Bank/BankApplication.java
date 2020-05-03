package com.example.Bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class BankApplication {
	private static BankService bank;
	public static void main(String[] args) {

		ApplicationContext apl=SpringApplication.run(BankApplication.class, args);
		bank=apl.getBean(BankService.class);
		User u=new User("2","1","1","1","1","1");
		User u2=new User("110","1","1","1","1","1");

		bank.createUser(u);
		bank.createUser(u2);


		bank.addCard(u);
		bank.addCard(u);
		bank.addMoney(140, -1000);
		//bank.deleteUser(1);

        List<User> l=bank.getAllUsers();
		System.out.println(l);
		//System.out.println(bank.getPlace(u));

	}

}
