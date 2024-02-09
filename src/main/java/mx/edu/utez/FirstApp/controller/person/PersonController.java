package mx.edu.utez.FirstApp.controller.person;

import jakarta.validation.Valid;
import mx.edu.utez.FirstApp.config.ApiResponse;
import mx.edu.utez.FirstApp.controller.person.dto.PersonDto;
import mx.edu.utez.FirstApp.service.person.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person")
@CrossOrigin(origins = {"*"})
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll(){
        return service.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody PersonDto personDto){
        return service.save(personDto.toEntity());
    }

}
