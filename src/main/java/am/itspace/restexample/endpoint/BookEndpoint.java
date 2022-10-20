package am.itspace.restexample.endpoint;

import am.itspace.restexample.model.Book;
import am.itspace.restexample.model.BookLanguage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookEndpoint {

    List<Book> books = new ArrayList<>(List.of(
            new Book(1, "girq 1", "poxos", 34.5, BookLanguage.EN),
            new Book(2, "girq 2", "petros", 74.5, BookLanguage.ARM),
            new Book(3, "girq 3", "martiros", 24.5, BookLanguage.RU)
    ));

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return books;
    }

    @GetMapping("/books/{id}")
    private ResponseEntity<Book> getBookId(@PathVariable("id") int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return ResponseEntity.ok(book);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/books")
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        for (Book bookFromDb : books) {
            if (bookFromDb.getTitle().equals(book.getTitle())
                    && bookFromDb.getAuthorName().equals(book.getAuthorName())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }

        }
        books.add(book);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/books")
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        if (book.getId() > 0) {
            for (Book bookFromDb : books) {
                if (bookFromDb.getId() == book.getId()) {
                    bookFromDb.setBookLanguage(book.getBookLanguage());
                    bookFromDb.setTitle(book.getTitle());
                    bookFromDb.setPrice(book.getPrice());
                    bookFromDb.setAuthorName(book.getAuthorName());
                    return ResponseEntity.ok(bookFromDb);
                }
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable("id") int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                books.remove(book);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

}
