package mx.edu.utez.FirstApp.model.person;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.FirstApp.model.user.User;

import java.time.LocalDate;

@Entity
@Table(name = "people")
@NoArgsConstructor
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 50, nullable = false)
    private String surname;
    @Column(length = 50)
    private String lastname;
    @Column(columnDefinition = "DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate birthDate;
    @Column(length = 18, nullable = false, unique = true)
    private String curp;
    @Column(columnDefinition = "BOOL DEFAULT true")
    private Boolean status; //Wrappers boolean

    //Person no depende de user para existir
    @OneToOne(mappedBy = "person", cascade = CascadeType.PERSIST) // Es el que mandeja la relación
    @JsonIgnoreProperties(value = {"person"}) //Arreglo de strings
    // Ignora las propiedades que se agregan en el usuario, no a la persona
    // Si no a la propiedad que va a tener
    private User user;

    public Person(String name, String surname, String lastname, String curp, LocalDate birthDate ) {
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.curp = curp;
    }

    public Person(Long id, String name, String surname, String lastname, String curp, LocalDate birthDate, User user) {
        Id = id;
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.curp = curp;
        this.user = user;
    }
}
