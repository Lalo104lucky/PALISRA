package mx.edu.utez.FirstApp.service.user;

import mx.edu.utez.FirstApp.model.user.User;
import mx.edu.utez.FirstApp.model.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserServices {

    private final UserRepository repository;

    public UserServices(UserRepository repository){
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Optional<User> findUserByUsername(String username){
        return repository.findFirstByUsername(username);
    }
}
