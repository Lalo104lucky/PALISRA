package mx.edu.utez.FirstApp.security.services;

import mx.edu.utez.FirstApp.model.user.User;
import mx.edu.utez.FirstApp.security.entity.UserDetailsImpl;
import mx.edu.utez.FirstApp.service.user.UserServices;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserDetailsServicesImpl implements UserDetailsService {

    private final UserServices services;

    public UserDetailsServicesImpl(UserServices services){
        this.services = services;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> foundUser = services.findUserByUsername(username);
        if(foundUser.isPresent())
            return UserDetailsImpl.build(foundUser.get());
        throw new UsernameNotFoundException("UserNotFound");
    }
}
