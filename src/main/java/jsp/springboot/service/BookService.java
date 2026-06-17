package jsp.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Book;
import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.NoRecordAvailableException;
import jsp.springboot.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	
	public ResponseStructure<Book> saveBook(Book book){
		
		ResponseStructure<Book> res = new ResponseStructure<Book>();
		
		res.setStatusCode(HttpStatus.CREATED.value());
		res.setMessage("Book record saved");
		res.setData(bookRepository.save(book));
		
		return res;
	}
	
	
	public ResponseStructure<List<Book>> saveAllBook(List<Book> books){
		
		ResponseStructure<List<Book>> res = new ResponseStructure<>();
		
		res.setStatusCode(HttpStatus.CREATED.value());
		res.setMessage("Books record Saved!!");
		res.setData(bookRepository.saveAll(books));
		
		return res;
	}

	
	public ResponseStructure<List<Book>> fetchAllBook(){
		
	ResponseStructure<List<Book>> res = new ResponseStructure<>();
			
			res.setStatusCode(HttpStatus.FOUND.value());
			res.setMessage("Data fetched successfully");
			res.setData(bookRepository.findAll());
			
			return res;
	}
	
	
	public ResponseStructure<Book> fetchBookById(Integer id){
				
		ResponseStructure<Book> res = new ResponseStructure<>();
			
		Optional<Book> opt = bookRepository.findById(id);
				
		if(opt.isPresent()) {
					
				res.setStatusCode(HttpStatus.FOUND.value());
				res.setMessage("Data fetched successfully");
				res.setData(opt.get());
					
				return  res;
		}
		else 
				throw new IdNotFoundException("Id Not existing in DB");
	}
	
	
	
	public ResponseStructure<Book> updateBookRecord(Book book){
		
		ResponseStructure<Book> res = new ResponseStructure<>();
		
		if(book.getId()==null) 
			throw new IdNotFoundException("Id Not existing in DB");
		
		Optional<Book> opt = bookRepository.findById(book.getId());
		if(opt.isPresent()) {
			
			res.setStatusCode(HttpStatus.OK.value());
			res.setMessage("Record Updated of : " );
			res.setData(bookRepository.save(book));
			
			return res;
		}
		else 
			throw new IdNotFoundException("Id Not existing in DB");
		
	}
	
	
	public ResponseStructure<String> deleteBookRecord(Integer id){
		ResponseStructure<String> res = new ResponseStructure<>();
		
		Optional<Book> opt = bookRepository.findById(id);
		
		if(opt.isPresent()) {
			res.setStatusCode(HttpStatus.OK.value());
			res.setMessage("Record is Deleted!!!");
			res.setData("Success");
			bookRepository.delete(opt.get());
			
			return res;
		}
		
		else
			throw new IdNotFoundException("Id Not existing in DB");
	}
	
	public ResponseStructure<List<Book>> fetchBookByAuthor(String author){
		List<Book> books = bookRepository.findByAuthor(author);
		
		ResponseStructure<List<Book>> res = new ResponseStructure<List<Book>>();
		
		if(!books.isEmpty()) {
			res.setStatusCode(HttpStatus.OK.value());
			res.setMessage("Book record with author :"+author+" retrieved");
			res.setData(books);
			
			return res;
		}
		else 
			throw new NoRecordAvailableException("Book record does not exist!!");
	}
	
	
	public ResponseStructure<Book> fetchBookByAuthorAndTitle(String author, String title){

		ResponseStructure<Book> res = new ResponseStructure<Book>();
		
		Optional<Book> opt = bookRepository.findByAuthorAndTitle(author, title);
		
		if(opt.isPresent()) {
			res.setStatusCode(HttpStatus.FOUND.value());
			res.setMessage("Book record with author and title retrive");
			res.setData(opt.get());
			
			return res;
		}
		else 
			throw new NoRecordAvailableException("Book record does not exist!!");
	}
	
	
	public ResponseStructure<List<Book>> fetchPriceLessThan(@PathVariable Integer price){
		
		ResponseStructure<List<Book>> res = new ResponseStructure<List<Book>>();
		
		List<Book> books =  bookRepository.findByPriceLessThan(price);
		
		if(!books.isEmpty()) {
			res.setStatusCode(HttpStatus.FOUND.value());
			res.setMessage("Book record with price less than Retrive");
			res.setData(books);
			
			return res;
		}
		else
			throw new NoRecordAvailableException("Book record does not exist!!");
	}
	
	
	public ResponseStructure<List<Book>> fetchPriceBetween(@PathVariable Double startRange, @PathVariable Double endRange){
		
		ResponseStructure<List<Book>> res = new ResponseStructure<List<Book>>();
		
		List<Book> books = bookRepository.findByPriceBetween(startRange, endRange);
		
		if(!books.isEmpty()) {
			res.setStatusCode(HttpStatus.OK.value());
			res.setMessage("the book in the price range are : ");
			res.setData(books);
			
			return res;
		}
		else
			throw new NoRecordAvailableException("Book record not exist in the DB");
	}
	
	
	public ResponseStructure<List<Book>> fetchAvialableBooks(){
		
		ResponseStructure<List<Book>> res = new ResponseStructure<List<Book>>();
		
		List<Book> books = bookRepository.getBookByAvailability();
		
		if(!books.isEmpty()) {
			res.setStatusCode(HttpStatus.OK.value());
			res.setMessage("The available books are ");
			res.setData(books);
			
			return res;
		}
		else
			throw new NoRecordAvailableException("Book is not avaialable .. ");
	}
	
	
	
	public ResponseStructure<List<Book>> fetchByPublishYear(Integer year){
		
		ResponseStructure<List<Book>> res = new ResponseStructure<List<Book>>();
		
		List<Book> books = bookRepository.getBookByPublishedYear(year);
		
		if(!books.isEmpty()) {
			res.setStatusCode(HttpStatus.OK.value());
			res.setMessage("The book by Publish Year is fetched");
			res.setData(books);
			
			return res;
		}
		else
			throw new NoRecordAvailableException("Books are not avialabe with these publish year");
	}
	
	
	
	
	public ResponseStructure<List<Book>> fetchByGenre(String genre){
		
		ResponseStructure<List<Book>> res = new ResponseStructure<List<Book>>();
		
		List<Book> books = bookRepository.getBookByGenre(genre);
		
		if(!books.isEmpty()) {
			res.setStatusCode(HttpStatus.OK.value());
			res.setMessage("The book by genre is fetched");
			res.setData(books);
			
			return res;
		}
		else
			throw new NoRecordAvailableException("Books are not avialabe with these genre");
	}
	
	
	public ResponseStructure<Page<Book>> getBookByPagination(int pageNumber, int pageSize) {
		
		Page<Book> pages = bookRepository.findAll(PageRequest.of(pageNumber, pageSize));
		
		ResponseStructure<Page<Book>> res = new ResponseStructure<Page<Book>>();
		
		if(!pages.isEmpty()) {
			res.setStatusCode(HttpStatus.OK.value());
			res.setMessage("Book record retrieved by pagination");
			res.setData(pages);
			
			return res;
		}
		else {
			throw new NoRecordAvailableException("No record avialable in DB");
		}
	}
	
}
