package jsp.springboot.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Book;
import jsp.springboot.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	

	// insert a record     –––––––––––––––––––––––––––––––––––––––––––––––––>
	
	// T save(T reference)
	@PostMapping
	public ResponseEntity<ResponseStructure<Book>> saveBook(@RequestBody Book book) {

		return new ResponseEntity<ResponseStructure<Book>>(bookService.saveBook(book), HttpStatus.CREATED);
	}
	
	
	// insert multiple records –––––––––––––––––––––––––––––––––––––––––––––>
	
	@PostMapping("/books")
	public ResponseEntity<ResponseStructure<List<Book>>> saveBook(@RequestBody List<Book> books) {
		
		return new ResponseEntity<ResponseStructure<List<Book>>>(bookService.saveAllBook(books), HttpStatus.CREATED);
	}
	
	
	// fetch a Record ––––––––––––––––––––––––––––––––––––––––––––––––>
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Book>>> getAllBooks(){
		
		return new ResponseEntity<ResponseStructure<List<Book>>>(bookService.fetchAllBook(), HttpStatus.FOUND);
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Book>> getBookById(@PathVariable Integer id) {
		
		return new ResponseEntity<ResponseStructure<Book>>(bookService.fetchBookById(id), HttpStatus.FOUND);
	}
	
	// fetch by page ––––––––––––––––––––––––––––––––––>
	
	@GetMapping("/page/{pagenumber}/{pagesize}")
	public ResponseEntity<ResponseStructure<Page<Book>>> getBookByPage(@PathVariable int pagenumber, @PathVariable int pagesize){
		return new ResponseEntity<>(bookService.getBookByPagination(pagenumber, pagesize), HttpStatus.OK);
	}
	
	
	// update a Record ––––––––––––––––––––––––––––––––––––––––––––––––––––>
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseStructure<Book>> updateBook(@RequestBody Book book) {
		
		return new ResponseEntity<ResponseStructure<Book>>(bookService.updateBookRecord(book),HttpStatus.OK);
	}
	

	// Delete a record from the DataBase ––––––––––––––––––––––––––––––––––––––––––––––––––––» 
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteBook(@PathVariable Integer id) {
		
		return new ResponseEntity<ResponseStructure<String>>(bookService.deleteBookRecord(id), HttpStatus.OK);
	}
	
	
	// Custom Method of Exceptions –––––––––––––––––––––––––––––––––––––––––––––––––––––––––––»
	
	@GetMapping("/author/{author}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByAuthor(@PathVariable String author){
		
		return new ResponseEntity<>(bookService.fetchBookByAuthor(author), HttpStatus.OK);
	}
	
	
	@GetMapping("/authorAndTitle/{author}/{title}")
	public ResponseEntity<ResponseStructure<Book>> getBookByAuthorAndTitle(@PathVariable String author, @PathVariable String title){
		
		return new ResponseEntity<ResponseStructure<Book>>(bookService.fetchBookByAuthorAndTitle(author, title), HttpStatus.FOUND);
	}
	
	
	@GetMapping("/priceLessThan/{price}")
	public ResponseEntity<ResponseStructure<List<Book>>> findPriceLessThan(@PathVariable Integer price){
		
		return new ResponseEntity<ResponseStructure<List<Book>>>(bookService.fetchPriceLessThan(price), HttpStatus.FOUND);
	}
	
	
	@GetMapping("/priceBetween/{startRange}/{endRange}")
	public ResponseEntity<ResponseStructure<List<Book>>> findPriceBetween(@PathVariable Double startRange, @PathVariable Double endRange){
		
		return new ResponseEntity<ResponseStructure<List<Book>>>(bookService.fetchPriceBetween(startRange, endRange), HttpStatus.OK);
	}
	
	
	@GetMapping("/avialableBooks")
	public ResponseEntity<ResponseStructure<List<Book>>> findAvialableBooks(){
		
		return new ResponseEntity<ResponseStructure<List<Book>>>(bookService.fetchAvialableBooks(), HttpStatus.OK);
	}
	
	
	@GetMapping("/year/{year}")
	public ResponseEntity<ResponseStructure<List<Book>>> publishYear(@PathVariable Integer year){
		
		return new ResponseEntity<ResponseStructure<List<Book>>>(bookService.fetchByPublishYear(year), HttpStatus.OK);
	}
	
	
	@GetMapping("/genre/{genre}")
	public ResponseEntity<ResponseStructure<List<Book>>> bookGenre(@PathVariable String genre){
		
		return new ResponseEntity<ResponseStructure<List<Book>>>(bookService.fetchByGenre(genre), HttpStatus.OK);
	}

}
