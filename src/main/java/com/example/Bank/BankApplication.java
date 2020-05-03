package com.example.Bank;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
@Controller
public class BankApplication {

	public static BankService bankService;

	public static void main(String[] args) {
		ApplicationContext apl=SpringApplication.run(BankApplication.class, args);
		bankService=apl.getBean(BankService.class);
	}


	@RequestMapping({"/registration", ""})
	public String  getRegisterPage(Model model){

		return "registration";
	}
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String setPage(@ModelAttribute User user, Model model){

		System.out.println(user);
		return "index";


	}


}
