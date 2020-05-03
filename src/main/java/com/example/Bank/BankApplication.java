package com.example.Bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@Controller
public class BankApplication {
	boolean enter=false;
	private static User user;
	public static BankService bankService;

	public static void main(String[] args) {
		ApplicationContext apl=SpringApplication.run(BankApplication.class, args);
		bankService=apl.getBean(BankService.class);
	}

	@RequestMapping({"/", "/"})
	public String first(){
		return "/startpage";
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
		return "login";
	}

	@RequestMapping(value = "/mainpage", method = RequestMethod.GET)
	public String getUser(Model model){
		if(enter==false)
			return "redirect:/enter";
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



}
