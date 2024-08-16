package com.bookStore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.bookStore.entity.Book;
import com.bookStore.entity.MyBookList;
import com.bookStore.service.BookService;
import com.bookStore.service.MyBookListService;
import com.bookStore.utils.NetworkUtils;

import java.util.*;

@Controller
public class BookController {

    private static final Logger logInfo = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private BookService service;
	
	@Autowired
	private MyBookListService myBookService;

     // Utility methods to get hostname and IP address
     private String getHostName() {
        return NetworkUtils.getHostName();
    }

    private String getHostAddress() {
        return NetworkUtils.getHostAddress();
    }
	
	@GetMapping("/")
	public String home() {
        String hostName = getHostName();
        String hostAddress = getHostAddress();

        logInfo.info("Welcome to BookStore Application !!!,  Enjoy the application !!!!");
        logInfo.debug("ClususTask JavaApplication in Dockercompose and sending logs to ELK Stack");
        logInfo.info("Running on Hostname: {} with IP Address: {}", hostName, hostAddress);
		return "home";
	}
	
	@GetMapping("/book_register")
	public String bookRegister() {
        String hostName = getHostName();
        String hostAddress = getHostAddress();
        logInfo.info("Registering New BookList... ");
        logInfo.info("Registered from: Running Hostname: {} with IP Address: {}", hostName, hostAddress);
		return "bookRegister";
	}
	
	@GetMapping("/available_books")
	public ModelAndView getAllBook() {
		List<Book>list=service.getAllBook();
//		ModelAndView m=new ModelAndView();
//		m.setViewName("bookList");
//		m.addObject("book",list);
        String hostName = getHostName();
        String hostAddress = getHostAddress();
        logInfo.info("Available books...");
        logInfo.info("Available books from: Running Hostname: {} with IP Address: {}", hostName, hostAddress);
		return new ModelAndView("bookList","book",list);
	}
	
	@PostMapping("/save")
	public String addBook(@ModelAttribute Book b) {
        logInfo.info("Saving books...");
		service.save(b);
        
		return "redirect:/available_books";
	}
	@GetMapping("/my_books")
	public String getMyBooks(Model model)
	{
		List<MyBookList>list=myBookService.getAllMyBooks();
		model.addAttribute("book",list);
		return "myBooks";
	}
	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id") int id) {
		Book b=service.getBookById(id);
		MyBookList mb=new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
		myBookService.saveMyBooks(mb);
        String hostName = getHostName();
        String hostAddress = getHostAddress();
        logInfo.info("My BookList: {}",id);
        logInfo.info("BookList from: Running Hostname: {} with IP Address: {}", hostName, hostAddress);
		return "redirect:/my_books";
	}
	
	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id,Model model) {
		Book b=service.getBookById(id);
		model.addAttribute("book",b);
        String hostName = getHostName();
        String hostAddress = getHostAddress();
        logInfo.info("Edited BookList ID: {}",id);
        logInfo.info("Edited from: Running Hostname: {} with IP Address: {}", hostName, hostAddress);
		return "bookEdit";
	}
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id")int id) {
		service.deleteById(id);
        String hostName = getHostName();
        String hostAddress = getHostAddress();
        logInfo.info("Deleted BookList ID: {}",id);
        logInfo.info("Deleted from: Running Hostname: {} with IP Address: {}", hostName, hostAddress);
		return "redirect:/available_books";
	}
	
}

