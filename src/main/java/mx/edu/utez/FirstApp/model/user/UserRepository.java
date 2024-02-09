package mx.edu.utez.FirstApp.model.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface
UserRepository extends JpaRepository<User, Long> {

    //method queries
    //select * from users where username = ?;

    Optional<User> findFirstByUsername(String username);
    List<User> findAllByStatus(boolean status);
    Optional<User> findByPersonCurp (String curp);
    List<User> findAllByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    /*
    1. Obtener todos los usuarios por medio del status
    2. Obtener un usuario por medio de la curp
    3. Obtener a todos los usuarios (createdAt) que se encuentren entre un rango de fechas

     */
}
