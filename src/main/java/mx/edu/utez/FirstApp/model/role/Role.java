package mx.edu.utez.FirstApp.model.role;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import mx.edu.utez.FirstApp.model.user.User;

import java.util.Set;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 25, nullable = false, unique = true)
    private String name;

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    private Set<User> users;

    public Role(String name){
        this.name = name;
    }
}
