package am.itspace.restexample.dto;

import am.itspace.restexample.model.Gender;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorResponseDto {
    private int id;
    private String name;
    private String surname;
    private Gender gender;
}
