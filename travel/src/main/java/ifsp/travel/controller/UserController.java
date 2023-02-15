package ifsp.travel.controller;
import ifsp.travel.model.entity.User;
import ifsp.travel.model.dto.UserRequestDTO;
import ifsp.travel.model.dto.UserResponseDTO;
import ifsp.travel.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/travel")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    @Transactional
    public ResponseEntity<?> createUser(
            @RequestBody UserRequestDTO userRequestDTO) {

        UserResponseDTO saveUser = userService.create(userRequestDTO);

        if(saveUser.getError()==null){
            return ResponseEntity.ok(String.valueOf(saveUser.getId()));
        }
        return ResponseEntity.badRequest().body(saveUser.getError());
    }

    @GetMapping("/user")
    @Transactional
    public ResponseEntity<?> getUser(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password) {

        UserResponseDTO response = userService.authenticate(username,password);
        if (response.getError()==null){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response.getError());
    }

    @DeleteMapping("/user")
    @Transactional
    public ResponseEntity deleteUser(
            @RequestParam(value = "id", required = true) Long id) {
        UserResponseDTO userResponseDTO = userService.delete(id);
        if(userResponseDTO.getError()==null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body(userResponseDTO.getError());
    }

    @GetMapping("/users")
    @Transactional
    public ResponseEntity<List<User>> getUsers(){
        List<User> response = userService.getAllUsers();
        return ResponseEntity.ok(response);
    }
}
