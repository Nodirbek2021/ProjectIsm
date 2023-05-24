package uz.pdp.projectism.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.projectism.entity.User;
import uz.pdp.projectism.payload.ApiResponse;
import uz.pdp.projectism.payload.UserDTO;

import java.util.UUID;

public interface UserService {
    ApiResponse<?> addUser( UserDTO userDTO);

    ApiResponse<?>getById( UUID id);

    ApiResponse<?>getAllUsers();

    ApiResponse<?>editUser( UUID id,  UserDTO userDTO);

    ApiResponse<?>deleteUser( UUID id);




}
