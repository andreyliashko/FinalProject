package com.example.Bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Controller
public class BankApplication {
	boolean enter=false;
	private  User user;
	public static BankService bankService;
	public static List<String> list=new ArrayList<>();

	public static void main(String[] args) {
		ApplicationContext apl=SpringApplication.run(BankApplication.class, args);
		bankService=apl.getBean(BankService.class);
	}

	@RequestMapping({"/", "/"})
	public String first(){
		return "startpage";
	}
	@RequestMapping({"/registration", ""})
	public String  getRegisterPage(Model model){

		return "registration";
	}
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String setPage(@ModelAttribute User user, Model model){

		System.out.println("create new user - "+user);
		bankService.createUser(user);
		model.addAttribute("users", bankService.getAllUsers());
		return "redirect:/mainpage";


	}
	@RequestMapping(value = "/enter")
	public String enterPage(){
		this.enter=false;
		return "login";
	}

	@RequestMapping(value = "/mainpage", method = RequestMethod.GET)
	public String getUser(Model model){
		if(enter==false)
			return "redirect:/enter";
		model.addAttribute("currentUser", user);
		return "mainpage";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String enterToAcc(@RequestParam("login")String log, @RequestParam("password")String password){
		long i=bankService.userEnter(log, password);
		if(i>=0){
			user=bankService.getUser(i);
			enter=true;
		}
		return "redirect:/mainpage";
	}
	@RequestMapping(value = "/addCard")
	public String getNewCard(){
		if(enter!=true)
			return "redirect:/enter";
		bankService.addCard(user);
		user=bankService.getUser(user.getId());
		return "redirect:/mainpage";
	}
	@RequestMapping({"/replenish", ""})
	public String getPage(){
		if(enter!=true)
			return "redirect:/enter";
		return "replenish";
	}
	@RequestMapping(value = "/replenish", method = RequestMethod.POST)
	public String addMoney(@RequestParam("cardNum") long cardnum, @RequestParam("sum")long sum) {

		if(enter!=true)
			return "redirect:/enter";
		bankService.addMoney(cardnum, sum);
		user=bankService.getUser(user.getId());
		return "redirect:/mainpage";
	}
	@RequestMapping(value = "/send")
	public String transactPage(){
		if(enter==false)
			return "redirect:/enter";
		return "transfer";
	}
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public String transfer(@RequestParam("card1") long card1, @RequestParam("card2")long card2, @RequestParam("sum")long sum){
		if(enter!=true)
			return "redirect:/enter";
		if(sum<=0||!user.containsCard(card1)) return "redirect:/mainpage";
		bankService.sendFromCardToCard(card1, card2, sum);
		user=bankService.getUser(user.getId());
		return "redirect:/mainpage";
	}





}
