package ifsp.travel.service;

import ifsp.travel.model.entity.User;
import ifsp.travel.model.dto.UserRequestDTO;
import ifsp.travel.model.dto.UserResponseDTO;
import ifsp.travel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

import static ifsp.travel.model.Constants.*;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserResponseDTO create(UserRequestDTO requestDTO) throws Exception {

        if (!ObjectUtils.isEmpty(repository.findByUsername(requestDTO.getUsername()))) {
            return UserResponseDTO.builder().error("Usuário já cadastrado.").build();
        }

        User user = User.builder()
                .username(requestDTO.getUsername())
                .password(requestDTO.getPassword())
                .name(requestDTO.getName())
                .email(requestDTO.getEmail())
                .phone(requestDTO.getPhone())
                .profileType(getProfileType(requestDTO.getUsername()))
                .hotels(new ArrayList<>())
                .flights(new ArrayList<>())
                .packages(new ArrayList<>())
                .build();
        repository.save(user);

        return UserResponseDTO.builder().build();
    }

    public UserResponseDTO update(Long id, UserRequestDTO requestDTO) {
        User user = repository.findById(id).get();
        if (user ==null){
            return UserResponseDTO.builder().error("Usuário não existe.").build();
        }

        repository.save(User.builder()
                .id(user.getId())
                .username(requestDTO.getUsername())
                .name(requestDTO.getUsername())
                .email(requestDTO.getEmail())
                .phone(requestDTO.getPhone())
                .password(requestDTO.getPassword())
                .phone(requestDTO.getPhone())
                .email(requestDTO.getEmail())
                .build());

        return UserResponseDTO.builder().build();
    }

    public UserResponseDTO delete(Long id) {
        User user = repository.findById(id).get();
        if (!ObjectUtils.isEmpty(user)) {
            repository.delete(user);
            return UserResponseDTO.builder().build();
        }
        return UserResponseDTO.builder().error("Usuário não existe.").build();
    }

    public UserResponseDTO authenticate(String username, String password) {
        User user = repository.findByUsername(username);
        if (!ObjectUtils.isEmpty(user) && user.getPassword().equals(password)) {
            return UserResponseDTO.builder()
                    .username(user.getUsername())
                    .name(user.getName())
                    .email(user.getEmail())
                    .phone(user.getPhone())
                    .profileType(user.getProfileType())
                    .hotels(user.getHotels())
                    .flights(user.getFlights())
                    .packages(user.getPackages())
                    .messages(user.getMessages())
                    .build();
        }
        if (!ObjectUtils.isEmpty(user) && !user.getPassword().equals(password)) {
                return UserResponseDTO.builder().error("Dados incorretos!").build();
            }

        return UserResponseDTO.builder().error("Usuário não existe.").build();
    }

    private String getProfileType(String username) throws Exception {
        if (username.length() == SIZE_CPF){
            return CPF;
        } else if (username.length() == SIZE_CNPJ){
            return CNPJ;
        } else {
            throw new Exception("Numero do cpf/cnpf inválido!");
        }
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }
}