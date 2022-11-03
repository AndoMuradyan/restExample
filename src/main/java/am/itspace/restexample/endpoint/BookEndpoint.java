package am.itspace.restexample.endpoint;

import am.itspace.restexample.model.Book;
import am.itspace.restexample.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BookEndpoint {

    private final BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    private ResponseEntity<Book> getBookId(@PathVariable("id") int id) {
        Optional<Book> byId = bookRepository.findById(id);
        if (byId.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(byId.get());
    }

    @PostMapping("/books")
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        bookRepository.save(book);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/books")
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        if (book.getId() == 0) {
            return ResponseEntity.badRequest().build();
        }
        bookRepository.save(book);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable("id") int id) {
        bookRepository.deleteById(id);
        return ResponseEntity.notFound().build();
    }

}
