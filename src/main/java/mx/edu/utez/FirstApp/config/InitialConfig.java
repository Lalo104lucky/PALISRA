package mx.edu.utez.FirstApp.config;

import mx.edu.utez.FirstApp.model.person.PersonRepository;
import mx.edu.utez.FirstApp.model.role.Role;
import mx.edu.utez.FirstApp.model.role.RoleRepository;
import mx.edu.utez.FirstApp.model.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Configuration
public class InitialConfig implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PersonRepository personRepository;

    public InitialConfig(RoleRepository roleRepository, UserRepository userRepository, PersonRepository personRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.personRepository = personRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        Role admminRole = finfOrSaveRole(new Role("ADMIN ROLE"));
        Role clientRole = finfOrSaveRole(new Role("CLIENT ROLE"));
        Role userRole = finfOrSaveRole(new Role("USER ROLE"));


    }

    @Transactional(readOnly = true, rollbackFor = {SQLException.class})
    public Role finfOrSaveRole(Role role){
        Optional<Role> foundRole = roleRepository.findByName(role.getName());
        return foundRole.orElseGet(() -> roleRepository.saveAndFlush(role));
    }
}
