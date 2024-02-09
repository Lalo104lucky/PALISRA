package mx.edu.utez.FirstApp.controller.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.FirstApp.model.role.Role;
import mx.edu.utez.FirstApp.model.user.User;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String avatar;
    private Set<Role> roles;

    public User toEntity(){
        return new User(id, username, password, avatar, roles);
    }
}
