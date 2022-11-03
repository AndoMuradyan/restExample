package am.itspace.restexample.endpoint;

import am.itspace.restexample.dto.AuthorResponseDto;
import am.itspace.restexample.dto.CreateAuthorDto;
import am.itspace.restexample.mapper.AuthorMapper;
import am.itspace.restexample.model.Author;
import am.itspace.restexample.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthorEndpoint {


    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @GetMapping("/authors")
    public List<AuthorResponseDto> getAllAuthors() {
        return authorMapper.map(authorRepository.findAll());
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<Author> getAuthorId(@PathVariable("id") int id) {
        Optional<Author> byId = authorRepository.findById(id);
        if (byId.isEmpty()) {

            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(byId.get());
    }

    @PostMapping("/authors")
    public ResponseEntity<?> createAuthor(@RequestBody CreateAuthorDto createAuthorDto) {
        Author saveAuthor = authorRepository.save(authorMapper.map(createAuthorDto));
        return ResponseEntity.ok(saveAuthor);
    }

    @PutMapping("/authors")
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author) {
        if (author.getId() == 0) {
            return ResponseEntity.badRequest().build();

        }
        authorRepository.save(author);
        return ResponseEntity.ok(author);
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<?> deleteAuthorById(@PathVariable("id") int id) {
        authorRepository.deleteById(id);
        return ResponseEntity.notFound().build();
    }
}
