package am.itspace.restexample.endpoint;

import am.itspace.restexample.exception.ResourceNotFoundException;
import am.itspace.restexample.model.Book;
import am.itspace.restexample.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//CRUD
@RestController
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookRepository bookRepository;


    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book getById(@PathVariable("id") int id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book does not exists"));
    }

    @PostMapping("/books")
    public Book create(@RequestBody Book book) {
        if (book.getId() > 0) {
            throw new RuntimeException("Id must be 0");
        }
        return bookRepository.save(book);
    }

    @PutMapping("/books/{id}")
    public Book update(@RequestBody Book book, @PathVariable("id") int id) {
        Book bookFromDB = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book does not exists"));
        bookFromDB.setTitle(book.getTitle());
        bookFromDB.setDescription(book.getDescription());
        bookFromDB.setAuthorName(book.getAuthorName());
        bookFromDB.setPrice(book.getPrice());
        return bookRepository.save(bookFromDB);
    }

    @DeleteMapping("/books/{id}")
    public void delete(@PathVariable("id") int id){
        bookRepository.delete(bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book does not exists")));
    }
}