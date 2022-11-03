package am.itspace.restexample.mapper;

import am.itspace.restexample.dto.AuthorResponseDto;
import am.itspace.restexample.dto.CreateAuthorDto;
import am.itspace.restexample.model.Author;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    Author map (CreateAuthorDto createAuthorDto);
    AuthorResponseDto map (Author author);

    List<AuthorResponseDto> map (List<Author> authorList);
}
