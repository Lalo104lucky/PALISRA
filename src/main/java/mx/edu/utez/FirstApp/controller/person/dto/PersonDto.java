package mx.edu.utez.FirstApp.controller.person.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.FirstApp.controller.user.dto.UserDto;
import mx.edu.utez.FirstApp.model.person.Person;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class PersonDto {

    private Long id;
    private String name;
    private String surname;
    private String lastname;
    private String curp;
    private LocalDate birthDate;
    private UserDto user;

    public Person toEntity(){
        if (user==null)
            return new Person(name, surname, lastname, curp, birthDate);
        return new Person(id, name, surname, lastname, curp, birthDate, user.toEntity());
    }
}
