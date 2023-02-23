package com.springboot.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class TestController {
	@RequestMapping("/hi")
	public String firstHandler() {
		System.out.println("home ");
		return "home";
	}
	
//	@RequestMapping("/books")
//	@ResponseBody
//	public String getBooks() {
//		Book book=new Book(12500,"Java Spring boot","ABC");
//		System.out.println(book);
//		return book.toString();
//	}
}
