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
		User u=new User("1","1","1","1","1","1");
		bank.createUser(u);
		bank.addCard(u);
		bank.addCard(u);
		//bank.addCard(u);
		//bank.addCard(u);

		List<User> l=bank.getAllUsers();
		System.out.println(l);
		//bank.getAllUsers();
	}

}
