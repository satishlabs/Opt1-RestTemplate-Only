package com.booksearchms.controlller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.booksearchms.info.BookInfo;
import com.booksearchms.info.BookPriceInfo;

@RestController
public class BookSearchController {
	
	@GetMapping("/mybook/{bookId}")
	public BookInfo getBookById(@PathVariable Integer bookId) {
		System.out.println("---BookController---getBookById()---: "+bookId); 

		BookInfo bookInfo = new BookInfo(bookId, "Master Spring Boot 2", "Satish", "JLC", "Java"); 
		
		String baseURL = "http://localhost:9000";
		System.out.println("Base URL : "+ baseURL); 
		
		String endPoint = baseURL+"/bookPrice/"+bookId;
		
		RestTemplate rt = new RestTemplate();
		ResponseEntity<BookPriceInfo> respEntity = rt.getForEntity(endPoint, BookPriceInfo.class);
		
		BookPriceInfo bookPriceInfo = respEntity.getBody();
		bookInfo.setPrice(bookPriceInfo.getPrice());
		bookInfo.setOffer(bookPriceInfo.getOffer());
		bookInfo.setServerPort(bookPriceInfo.getServerPort());
		
		return bookInfo;
		
	}
}
