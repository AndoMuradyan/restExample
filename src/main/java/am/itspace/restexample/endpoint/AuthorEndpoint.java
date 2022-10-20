package am.itspace.restexample.endpoint;

import am.itspace.restexample.model.Author;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AuthorEndpoint {
    List<Author> authors = new ArrayList<>(List.of(
            new Author(1, "poxos", "poxosyan", 17),
            new Author(2, "petros", "petrosyan", 25),
            new Author(3, "martiros", "martirosyan", 35)
    ));

    @GetMapping("/authors")
    public List<Author> getAllAuthors() {
        return authors;
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<Author> getAuthorId(@PathVariable("id") int id) {
        for (Author author : authors) {
            if (author.getId() == id) {
                return ResponseEntity.ok(author);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/authors")
    public ResponseEntity<?> createAuthor(@RequestBody Author author) {
        for (Author authorFromDb : authors) {
            if (authorFromDb.getName().equals(author.getName())
                    && authorFromDb.getSurname().equals(author.getSurname())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }
        authors.add(author);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/authors")
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author) {
        if (author.getId() > 0) {
            for (Author authorFromDb : authors) {
                if (authorFromDb.getId() == author.getId()) {
                    authorFromDb.setName(author.getName());
                    authorFromDb.setSurname(author.getSurname());
                    authorFromDb.setAge(author.getAge());
                    return ResponseEntity.ok(authorFromDb);
                }
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<?> deleteAuthorById(@PathVariable("id") int id) {
        for (Author author : authors) {
            if (author.getId() == id) {
                authors.remove(author);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}
